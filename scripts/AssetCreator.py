import os
import tkinter as tk
import tomllib
from tkinter import ttk
from pathlib import Path
from PIL import Image
from vcolorpicker import getColor, rgb2hex
from Utils import PaletteButton

from scripts import Utils

# ui components
root: tk.Tk
main_frame: tk.Frame
top_frame: tk.Frame
builder_frame: tk.Frame

# ui variables
save_tool: tk.IntVar
save_armor: tk.IntVar
save_fluid: tk.IntVar
selected_preview: int
selected_reference: int

# config attributes
tool_palette_save_location: str
armor_palette_save_location: str
fluid_texture_save_location: str
bucket_texture_save_location: str

# important variables
palettes: dict[str, list[tuple[int, int, int]]] = {}
tool_images: dict[str, dict[str, Image.Image]] = {}
reference_images: list[Image.Image] = []
selected_color: tuple[int, int, int] = (255, 255, 255)

# constants
top_height = 0.13
tool_types = ["Axe", "Boots", "Chestplate", "Graip", "Hammer", "Helmet", "Hoe", "Knife", "Leggings", "Mallet", "Mattock", "Maul", "Paxel", "Pickaxe", "Prybar", "Scythe", "Shovel", "Sword"]
texture_styles = ["Base", "Metal", "Shiny", "Soft"]


def init_tool_images() -> None:
    for tool in tool_types:
        for style in texture_styles:
            path = Path(__file__).parent / "resources" / "tool" / "modular" / tool.lower() / style.lower()
            parts = os.listdir(path)
            final = None
            for part in parts:
                try:
                    img_path = path / part
                    with Image.open(img_path) as source:
                        img = source.convert("RGBA")
                    if final is None:
                        final = Image.new("RGBA", img.size)
                    final.alpha_composite(img, dest=(0, 0))
                except FileNotFoundError as e:
                    print("FileNotFoundError", e)
            try:
                handle_path = Path(__file__).parent / "resources" / "tool" / "built" / "handle" / (tool.lower() + ".png")
                with Image.open(handle_path) as source:
                    img = source.convert("RGBA")
                final.alpha_composite(img)
            except FileNotFoundError:
                pass
            if not tool_images.keys().__contains__(tool):
                tool_images[tool] = {}
            tool_images[tool][style] = final



def create_top_frame(parent: tk.Frame) -> tk.Frame:
    frame = tk.Frame(parent, highlightbackground="black", highlightcolor="black", highlightthickness=1)
    frame.place(relwidth=1, relheight=top_height)

    new_asset_button = tk.Button(frame, name="new_asset_button", text="New Asset Set", command=new_asset_set)
    new_asset_button.place(relx=0.05, rely=0.2, relheight=0.6, relwidth=0.185)

    material_id_label = tk.Label(frame, name="material_id_label", text="Material ID")
    material_id_label.place(relx=0.28, rely=0.2, relheight=0.6, relwidth=0.07)
    material_id_field = tk.Entry(frame, name="material_id_field")
    material_id_field.place(relx=0.35, rely=0.2, relheight=0.6, relwidth=0.2)

    global save_tool
    save_tool = tk.IntVar()
    save_tool_checkbox = tk.Checkbutton(frame, name="save_tool_checkbox", text="Save Tool?", variable=save_tool)
    save_tool_checkbox.place(relx=0.65, rely=0.2, relheight=0.2, relwidth=0.1)
    global save_armor
    save_armor = tk.IntVar()
    save_armor_checkbox = tk.Checkbutton(frame, name="save_armor_checkbox", text="Save Armor?", variable=save_armor)
    save_armor_checkbox.place(relx=0.65, rely=0.4, relheight=0.2, relwidth=0.1)
    global save_fluid
    save_fluid = tk.IntVar()
    save_fluid_checkbox = tk.Checkbutton(frame, name="save_fluid_checkbox", text="Save Fluid?", variable=save_fluid)
    save_fluid_checkbox.place(relx=0.65, rely=0.6, relheight=0.2, relwidth=0.1)

    save_button = tk.Button(frame, name="save_button", text="Save", command=save_assets)
    save_button.place(relx=0.765, rely=0.2, relheight=0.6, relwidth=0.185)
    return frame


def create_palette_builder(parent: tk.Frame) -> tk.Frame:
    frame = init_frame(parent)

    palette_frame = tk.Frame(frame, name="palette_frame", highlightthickness=1, highlightcolor="black", highlightbackground="black")
    preview_frame = tk.Frame(frame, name="preview_frame", bg="green")
    reference_frame = tk.Frame(frame, name="reference_frame", bg="lightblue")

    palette_label = tk.Label(frame, name="palette_label", text="Palette:")
    palette_label.place(relx=0.2, rely=0.03, relheight=0.045, relwidth=0.08)
    palette_frame.place(relx=0.28, rely=0.02, relheight=0.06, relwidth=0.35)

    selected_color_label = tk.Label(frame, name="selected_color_label", text="Selected Color:")
    selected_color_label.place(relx=0.7, rely=0.03, relheight=0.045, relwidth=0.1)
    selected_color_button = tk.Button(frame, name="selected_color_button")
    selected_color_button.configure(command=lambda: update_selected_color(selected_color_button))
    selected_color_button.place(relx=0.81, rely=0.02, relheight=0.06, relwidth=0.06)

    palette_type_dropdown = ttk.Combobox(frame, name="palette_type_dropdown", values=["Tool", "Armor", "Fluid"], state="readonly")
    palette_type_dropdown.bind("<<ComboboxSelected>>", lambda e: build_palette_and_preview(palette_frame, preview_frame, palette_type_dropdown.get()))
    palette_type_dropdown.set("Tool")
    palette_type_dropdown.place(relx=0.02, rely=0.03, relheight=0.045, relwidth=0.1)

    preview_frame.place(relx=0.0, rely=0.1, relheight=0.9, relwidth=0.5)
    reference_frame.place(relx=0.5, rely=0.1, relheight=0.9, relwidth=0.5)

    build_palette_and_preview(palette_frame, preview_frame, palette_type_dropdown.get())
    build_reference_frame(reference_frame)

    return frame


def create_empty_builder(parent: tk.Frame) -> tk.Frame:
    frame = init_frame(parent)
    label = tk.Label(frame, name="new_asset_set_label", text="Create a new asset set to begin", font=("Arial", 20))
    label.pack(side="top", fill="both", expand=True)

    return frame


def init_frame(parent: tk.Frame) -> tk.Frame:
    frame = tk.Frame(parent, highlightbackground="black", highlightcolor="black", highlightthickness=1)
    frame.place(relwidth=1, relheight=1 - top_height, rely=top_height)
    return frame


def new_asset_set() -> None:
    global builder_frame
    global palettes
    palettes = {
        "Tool": [(255, 255, 255), (241, 241, 241), (228, 228, 228), (205, 205, 205), (180, 180, 180), (151, 151, 151), (108, 108, 108), (77, 77, 77)],
        "Armor": [(254, 254, 254), (245, 245, 245), (234, 234, 234), (221, 221, 221), (203, 203, 203), (183, 183, 183), (161, 161, 161), (140, 140, 140), (116, 116, 116)],
        "Fluid": [(244, 244, 244), (230, 230, 230), (211, 211, 211), (192, 192, 192), (174, 174, 174), (158, 158, 158), (136, 136, 136), (121, 121, 121), (105, 105, 105), (90, 90, 90)]
    }
    builder_frame.destroy()
    builder_frame = create_palette_builder(main_frame)


def build_palette_and_preview(palette_frame: tk.Frame, preview_frame: tk.Frame, palette_type: str) -> None:
    global palettes
    palette = palettes[palette_type]
    for child in palette_frame.winfo_children():
        child.destroy()
    print(palette)
    offset = 0.0
    width = 1.0 / len(palette)
    i = 0
    for color in palette:
        palette_button = PaletteButton(palette_frame, i, palette_type, name="palette_button_" + str(i), bg="#" + str(rgb2hex(color)), borderwidth=0)
        palette_button.bind("<Button-1>", update_palette_color)
        palette_button.place(relx=offset, relheight=1, relwidth=width)
        offset += width
        i += 1
        # palette_button.pack()


    image_frame = preview_frame


def build_reference_frame(reference_frame: tk.Frame) -> None:
    print("building reference frame")


def update_selected_color(selected_color_button: tk.Button, new_color: tuple[int, int, int] = None) -> None:
    global selected_color
    if new_color is None:
        selected_color = getColor(selected_color)
    else:
        selected_color = new_color
    selected_color_button.configure(bg="#" + str(rgb2hex(selected_color)))


def update_palette_color(event) -> None:
    global selected_color
    global palettes
    palette_button: PaletteButton = event.widget
    palette_button.configure(bg="#" + str(rgb2hex(selected_color)))
    palettes[palette_button.palette_type][palette_button.index] = selected_color


def save_assets() -> None:
    if save_tool.get() == 1:
        pass
    if save_armor.get() == 1:
        pass
    if save_fluid.get() == 1:
        pass


def main():
    global tool_palette_save_location
    global armor_palette_save_location
    global fluid_texture_save_location
    global bucket_texture_save_location
    with open("config.toml", "rb") as f:
        config = tomllib.load(f)
        tool_palette_save_location = config["output"]["tool_palette_save_location"]
        armor_palette_save_location = config["output"]["armor_palette_save_location"]
        fluid_texture_save_location = config["output"]["fluid_texture_save_location"]
        bucket_texture_save_location = config["output"]["bucket_texture_save_location"]

    init_tool_images()

    global root
    global main_frame
    global top_frame
    global builder_frame

    root = tk.Tk(baseName="Slag Asset Creator", className="SlagAssetCreator")
    root.title("Slag Asset Creator")
    root.geometry("900x500")
    main_frame = tk.Frame(root)
    main_frame.pack(side="top", fill="both", expand=True)

    top_frame = create_top_frame(main_frame)
    builder_frame = create_empty_builder(main_frame)

    root.mainloop()


if __name__ == '__main__':
    main()
