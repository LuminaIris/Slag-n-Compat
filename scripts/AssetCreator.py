import os
import tkinter as tk
import tomllib
import copy
import json
from tkinter import ttk
from tkinter.filedialog import askopenfilename
from pathlib import Path
from PIL import Image
from vcolorpicker import getColor, rgb2hex
from Utils import PaletteButton, PreviewButton, ReferenceButton, Mapping

# ui components
root: tk.Tk
main_frame: tk.Frame
top_frame: tk.Frame
builder_frame: tk.Frame
selected_color_button: tk.Button
palette_buttons: list[PaletteButton] = []

# ui variables
material_id: tk.StringVar
save_tool: tk.IntVar
save_armor: tk.IntVar
save_fluid: tk.IntVar
selected_preview: int
selected_style: int
selected_frame: int
selected_reference: str

# config attributes
tool_palette_save_location: str
armor_palette_save_location: str
fluid_texture_save_location: str
bucket_texture_save_location: str
should_update_atlas: bool
atlas_save_location: str
mod_id: str

# important variables
palettes: dict[str, list[tuple[int, int, int]]] = {}
preview_pixel_ids: dict[int, list[PreviewButton]] = {}
tool_images: dict[str, dict[str, Image.Image]] = {}
armor_images: dict[str, dict[str, Image.Image]] = {}
fluid_images: dict[str, dict[int, Image.Image]] = {}
fluid_frame_counts: dict[str, int] = {}
reference_images: dict[str, Image.Image] = {}
selected_color: tuple[int, int, int] = (255, 255, 255)

# constants
top_height = 0.13
tool_types = ["Axe", "Boots", "Chestplate", "Graip", "Hammer", "Helmet", "Hoe", "Knife", "Leggings", "Mallet", "Mattock", "Maul", "Paxel", "Pickaxe", "Prybar", "Scythe", "Shovel", "Sword"]
armor_types = ["layer_1", "layer_2"]
fluid_types = ["Still", "Flow", "Bucket"]
texture_styles = ["Base", "Metal", "Shiny", "Soft"]
default_palettes = {
    "Tool": [(255, 255, 255), (241, 241, 241), (228, 228, 228), (205, 205, 205), (180, 180, 180), (151, 151, 151), (108, 108, 108), (77, 77, 77)],
    "Armor": [(254, 254, 254), (245, 245, 245), (234, 234, 234), (221, 221, 221), (203, 203, 203), (183, 183, 183), (161, 161, 161), (140, 140, 140), (116, 116, 116)],
    "Fluid": [(244, 244, 244), (230, 230, 230), (211, 211, 211), (192, 192, 192), (174, 174, 174), (158, 158, 158), (136, 136, 136), (121, 121, 121), (105, 105, 105), (90, 90, 90)]
}


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


def init_armor_images():
    for layer in armor_types:
        for style in texture_styles:
            try:
                img1_path = Path(__file__).parent / "resources" / "armor" / (style + "_" + layer + ".png")
                img2_path = Path(__file__).parent / "resources" / "armor" / "plate" / (style + "_" + layer + ".png")
                with Image.open(img1_path) as source:
                    img1 = source.convert("RGBA")
                with Image.open(img2_path) as source:
                    img2 = source.convert("RGBA")
                img1.alpha_composite(img2, dest=(0, 0))
                if not armor_images.keys().__contains__(layer):
                    armor_images[layer] = {}
                armor_images[layer][style] = img1
            except FileNotFoundError as e:
                print("FileNotFoundError", e)

def init_fluid_images():
    for state in fluid_types:
        try:
            path: Path = Path(__file__)
            match state:
                case "Still":
                    path = Path(__file__).parent / "resources" / "fluid" / "still.png"
                case "Flow":
                    path = Path(__file__).parent / "resources" / "fluid" / "flow.png"
                case "Bucket":
                    path = Path(__file__).parent / "resources" / "fluid" / "bucket.png"
            image = Image.open(path).convert("RGBA")
            for x in range(int(image.width / 16)):
                for y in range(int(image.height / 16)):
                    final = image.crop((x * 16, y * 16, (x + 1) * 16, (y + 1) * 16))
                    frame_num = x * int(image.height / 16) + y
                    if not fluid_images.keys().__contains__(state):
                        fluid_images[state] = {}
                    fluid_images[state][frame_num] = final
                    fluid_frame_counts[state] = frame_num
        except FileNotFoundError as e:
            print("FileNotFoundError", e)


def create_top_frame(parent: tk.Frame) -> tk.Frame:
    frame = tk.Frame(parent, highlightbackground="black", highlightcolor="black", highlightthickness=1)
    frame.place(relwidth=1, relheight=top_height)

    new_asset_button = tk.Button(frame, name="new_asset_button", text="New Asset Set", command=new_asset_set)
    new_asset_button.place(relx=0.05, rely=0.2, relheight=0.6, relwidth=0.185)

    material_id_label = tk.Label(frame, name="material_id_label", text="Material ID")
    material_id_label.place(relx=0.28, rely=0.2, relheight=0.6, relwidth=0.07)
    global material_id
    material_id = tk.StringVar()
    material_id.set("")
    material_id_field = tk.Entry(frame, name="material_id_field", textvariable=material_id)
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
    preview_frame = tk.Frame(frame, name="preview_frame", highlightthickness=1, highlightcolor="black", highlightbackground="black")
    reference_frame = tk.Frame(frame, name="reference_frame", highlightthickness=1, highlightcolor="black", highlightbackground="black")

    palette_label = tk.Label(frame, name="palette_label", text="Palette:")
    palette_label.place(relx=0.2, rely=0.03, relheight=0.045, relwidth=0.08)

    selected_color_label = tk.Label(frame, name="selected_color_label", text="Selected Color:")
    selected_color_label.place(relx=0.7, rely=0.03, relheight=0.045, relwidth=0.1)
    global selected_color_button
    selected_color_button = tk.Button(frame, name="selected_color_button")
    selected_color_button.configure(command=lambda: update_selected_color())
    selected_color_button.place(relx=0.81, rely=0.02, relheight=0.06, relwidth=0.06)

    palette_type_dropdown = ttk.Combobox(frame, name="palette_type_dropdown", values=["Tool", "Armor", "Fluid"], state="readonly")
    palette_type_dropdown.bind("<<ComboboxSelected>>", lambda e: build_palette_and_preview(palette_frame, preview_frame, palette_type_dropdown.get()))
    palette_type_dropdown.set("Tool")
    palette_type_dropdown.place(relx=0.02, rely=0.03, relheight=0.045, relwidth=0.1)

    preview_frame.place(relx=0.0, rely=0.1, relheight=0.9, relwidth=0.5)
    reference_frame.place(relx=0.5, rely=0.1, relheight=0.9, relwidth=0.5)

    build_palette_and_preview(palette_frame, preview_frame, palette_type_dropdown.get())
    build_reference_frame(reference_frame)

    palette_frame.place(relx=0.28, rely=0.02, relheight=0.06, relwidth=0.35)

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
    global material_id
    global selected_color
    global palettes
    palettes = copy.deepcopy(default_palettes)
    material_id.set("")
    selected_color = (255, 255, 255)
    builder_frame.destroy()
    builder_frame = create_palette_builder(main_frame)


def build_palette_and_preview(palette_frame: tk.Frame, preview_frame: tk.Frame, palette_type: str) -> None:
    for child in palette_frame.winfo_children():
        child.destroy()
    for child in preview_frame.winfo_children():
        child.destroy()
    global palettes
    palette = palettes[palette_type]
    offset = 0.0
    width = 1.0 / len(palette)
    i = 0
    palette_buttons.clear()
    for color in palette:
        palette_button = PaletteButton(palette_frame, i, palette_type, name=palette_type.lower() + "_palette_button_" + str(i), bg="#" + str(rgb2hex(color)), activebackground="#" + str(rgb2hex(color)), borderwidth=0)
        palette_button.bind("<Button-1>", update_palette_color)
        palette_button.place(relx=offset, relheight=1, relwidth=width)
        palette_buttons.append(palette_button)
        offset += width
        i += 1

    preview_label = tk.Label(preview_frame, name="preview_label", text="Preview")
    preview_label.place(relx=0.01, rely=0.005, relwidth=0.2, relheight=0.05)

    global selected_preview
    global selected_style
    global selected_frame
    if palette_type == "Tool":
        # tool has both preview type and style type
        tool_dropdown = ttk.Combobox(preview_frame, name="tool_dropdown", values=tool_types, state="readonly")
        tool_dropdown.bind("<<ComboboxSelected>>", lambda e: set_selected(image_frame, palette_type, tool_dropdown.get()))
        tool_dropdown.set("Sword")
        tool_dropdown.place(relx=0.05, rely=0.93, relwidth=0.2, relheight=0.05)
        selected_preview = tool_types.index(tool_dropdown.get())

        style_dropdown = ttk.Combobox(preview_frame, name="style_dropdown", values=texture_styles, state="readonly")
        style_dropdown.bind("<<ComboboxSelected>>", lambda e: set_style(image_frame, palette_type, style_dropdown.get()))
        style_dropdown.set("Base")
        style_dropdown.place(relx=0.3, rely=0.93, relwidth=0.2, relheight=0.05)
        selected_style = texture_styles.index(style_dropdown.get())
    elif palette_type == "Armor":
        # armor has layer and style type
        layer_dropdown = ttk.Combobox(preview_frame, name="layer_dropdown", values=armor_types, state="readonly")
        layer_dropdown.bind("<<ComboboxSelected>>", lambda e: set_selected(image_frame, palette_type, layer_dropdown.get()))
        layer_dropdown.set("layer_1")
        layer_dropdown.place(relx=0.05, rely=0.93, relwidth=0.2, relheight=0.05)
        selected_preview = armor_types.index(layer_dropdown.get())

        style_dropdown = ttk.Combobox(preview_frame, name="style_dropdown", values=texture_styles, state="readonly")
        style_dropdown.bind("<<ComboboxSelected>>", lambda e: set_style(image_frame, palette_type, style_dropdown.get()))
        style_dropdown.set("Base")
        style_dropdown.place(relx=0.3, rely=0.93, relwidth=0.2, relheight=0.05)
        selected_style = texture_styles.index(style_dropdown.get())
    elif palette_type == "Fluid":
        # fluid has preview type and frame change buttons
        fluid_dropdown = ttk.Combobox(preview_frame, name="tool_dropdown", values=fluid_types, state="readonly")
        fluid_dropdown.bind("<<ComboboxSelected>>", lambda e: set_selected(image_frame, palette_type, fluid_dropdown.get()))
        fluid_dropdown.set("Still")
        fluid_dropdown.place(relx=0.05, rely=0.93, relwidth=0.2, relheight=0.05)
        selected_preview = fluid_types.index(fluid_dropdown.get())

        prev_frame_button = tk.Button(preview_frame, name="prev_frame_button", text="<", command=lambda: change_selected_frame(image_frame, palette_type, -1))
        prev_frame_button.place(relx=0.025, rely = 0.465, relwidth=0.04, relheight=0.04)
        next_frame_button = tk.Button(preview_frame, name="next_frame_button", text=">", command=lambda: change_selected_frame(image_frame, palette_type, 1))
        next_frame_button.place(relx=0.935, rely=0.465, relwidth=0.04, relheight=0.04)
        selected_frame = 0

    else:
        error_label = tk.Label(preview_frame, text="Invalid palette type!")
        error_label.pack()
        return

    image_frame = tk.Frame(preview_frame, name="image_frame")
    build_image_frame(image_frame, palette_type)
    image_frame.place(relwidth=0.87, relheight=0.87, relx=0.065, rely=0.05)


def build_image_frame(image_frame: tk.Frame, palette_type: str) -> tk.Frame:
    for child in image_frame.winfo_children():
        child.destroy()
    build_preview(image_frame, palette_type)
    return image_frame


def build_preview(preview_frame: tk.Frame, palette_type: str) -> None:
    mapping: Mapping
    image: Image.Image
    if palette_type == "Tool":
        mapping = Mapping(Mapping.TOOL_IMAGE_TO_PALETTE)
        image = tool_images[tool_types[selected_preview]][texture_styles[selected_style]]
    elif palette_type == "Armor":
        mapping = Mapping(Mapping.ARMOR_IMAGE_TO_PALETTE)
        image = armor_images[armor_types[selected_preview]][texture_styles[selected_style]]
    elif palette_type == "Fluid":
        mapping = Mapping(Mapping.FLUID_IMAGE_TO_PALETTE)
        if fluid_types[selected_preview] == "Bucket":
            mapping = Mapping(Mapping.BUCKET_IMAGE_TO_PALETTE)
        image = fluid_images[fluid_types[selected_preview]][selected_frame]
    else:
        print("Invalid palette type!")
        return
    x_offset = 0
    x_width = 1.0 / image.width
    y_offset = 0
    y_height = 1.0 / image.height

    global preview_pixel_ids
    preview_pixel_ids = {}
    for x in range(image.width):
        for y in range(image.height):
            r, g, b, a = image.getpixel((x, y))
            if a != 0:
                palette_id = mapping.map((r, g, b))
                color: tuple[int, int, int]
                if palette_id == -1:
                    color = (r, g, b)
                else:
                    color = palettes[palette_type][palette_id]
                button = PreviewButton(preview_frame, palette_id, name="preview_pixel[{0},{1}]".format(x, y), bg="#" + str(rgb2hex(color)), activebackground="#" + str(rgb2hex(color)), borderwidth=0)
                button.bind("<Button-1>", update_palette_color)
                button.place(relx=x_offset, rely=y_offset, relwidth=x_width, relheight=y_height)
                if not preview_pixel_ids.keys().__contains__(palette_id):
                    preview_pixel_ids[palette_id] = []
                preview_pixel_ids[palette_id].append(button)
            y_offset += y_height
        x_offset += x_width
        y_offset = 0


def set_selected(preview_frame: tk.Frame, palette_type: str, value: str) -> None:
    global selected_preview
    global selected_frame
    if palette_type == "Tool":
        selected_preview = tool_types.index(value)
    elif palette_type == "Armor":
        selected_preview = armor_types.index(value)
    elif palette_type == "Fluid":
        selected_frame = 0
        selected_preview = fluid_types.index(value)
    build_image_frame(preview_frame, palette_type)


def set_style(preview_frame: tk.Frame, palette_type: str, value: str) -> None:
    global selected_style
    selected_style = texture_styles.index(value)
    build_image_frame(preview_frame, palette_type)


def change_selected_frame(preview_frame: tk.Frame, palette_type: str, amt: int) -> None:
    global selected_frame
    if palette_type != "Fluid":
        print("Selected frame should only be updated from fluid")
        return
    preview = fluid_types[selected_preview]
    frame_count = fluid_frame_counts[preview]
    selected_frame += amt
    if selected_frame < 0: selected_frame += frame_count
    if selected_frame >= frame_count: selected_frame -= frame_count
    build_image_frame(preview_frame, palette_type)


def build_reference_frame(reference_frame: tk.Frame) -> None:
    reference_label = tk.Label(reference_frame, name="reference_label", text="References")
    reference_label.place(relx=0.01, rely=0.005, relwidth=0.2, relheight=0.05)
    reference_dropdown = ttk.Combobox(reference_frame, name="reference_dropdown", values=list(reference_images.keys()), state="readonly")
    reference_dropdown.bind("<<ComboboxSelected>>", lambda e: build_reference_image(image_frame, reference_dropdown.get()))
    reference_dropdown.set(selected_reference)
    reference_dropdown.place(relx=0.05, rely=0.93, relwidth=0.4, relheight=0.05)

    new_reference_button = tk.Button(reference_frame, name="new_reference_button", text="New Reference", command=lambda: add_new_reference(image_frame, reference_dropdown))
    new_reference_button.place(relx=0.5, rely=0.93, relwidth=0.2, relheight=0.05)

    clear_references_button = tk.Button(reference_frame, name="clear_reference_button", text="Clear", command=lambda: clear_references(image_frame, reference_dropdown))
    clear_references_button.place(relx=0.75, rely=0.93, relwidth=0.2, relheight=0.05)

    image_frame = tk.Frame(reference_frame, name="image_frame")
    image_frame.place(relwidth=0.87, relheight=0.87, relx=0.065, rely=0.05)
    build_reference_image(image_frame, reference_dropdown.get())

def build_reference_image(image_frame: tk.Frame, reference_name: str) -> None:
    global selected_reference
    selected_reference = reference_name
    for child in image_frame.winfo_children():
        child.destroy()
    if reference_name == "<none>":
        label = tk.Label(image_frame, text="No reference images", font=("Arial", 20))
        label.pack(side="top", fill="both", expand=True)
    elif reference_name not in reference_images.keys():
        print("Unrecognized reference image!")
    else:
        image = reference_images[reference_name]
        x_offset = 0
        x_width = 1.0 / image.width
        y_offset = 0
        y_height = 1.0 / image.height

        for x in range(image.width):
            for y in range(image.height):
                r, g, b, a = image.getpixel((x, y))
                color = (r, g, b)
                if a != 0:
                    button = ReferenceButton(image_frame, color, name="reference_pixel[{0},{1}]".format(x, y), bg="#" + str(rgb2hex(color)), activebackground="#" + str(rgb2hex(color)), borderwidth=0)
                    button.bind("<Button-1>", lambda e: update_selected_color(e.widget.color))
                    button.place(relx=x_offset, rely=y_offset, relwidth=x_width, relheight=y_height)
                y_offset += y_height
            x_offset += x_width
            y_offset = 0


def add_new_reference(image_frame: tk.Frame, reference_dropdown: ttk.Combobox) -> None:
    path = Path(askopenfilename(title="Select reference image (.png)", filetypes=[("PNG files", "*.png")]))
    if path == Path("."):
        return
    image = Image.open(path)
    reference_images[path.name] = image.convert("RGBA")
    build_reference_image(image_frame, path.name)
    update_reference_dropdown(reference_dropdown, path.name)


def clear_references(image_frame: tk.Frame, reference_dropdown: ttk.Combobox) -> None:
    global reference_images
    reference_images = {}
    update_reference_dropdown(reference_dropdown)
    build_reference_image(image_frame, reference_dropdown.get())


def update_reference_dropdown(reference_dropdown: ttk.Combobox, new_value: str = "") -> None:
    reference_dropdown.configure(values=list(reference_images.keys()))
    if new_value != "": reference_dropdown.set(new_value)
    else: reference_dropdown.set("<none>")


def update_selected_color(new_color: tuple[int, int, int] = None) -> None:
    global selected_color
    global selected_color_button
    if new_color is None:
        r, g, b = getColor(selected_color)
        selected_color = (round(r), round(g), round(b))
    else:
        selected_color = new_color
    selected_color_button.configure(bg="#" + str(rgb2hex(selected_color)))


def update_palette_color(event) -> None:
    global palettes
    index = event.widget.index
    if index == -1: return
    palette_button: PaletteButton = palette_buttons[index]
    palette_button.configure(bg="#" + str(rgb2hex(selected_color)), activebackground="#" + str(rgb2hex(selected_color)))
    palettes[palette_button.palette_type][palette_button.index] = selected_color
    if preview_pixel_ids.keys().__contains__(index):
        for pixel in preview_pixel_ids[index]:
            pixel.configure(bg="#" + str(rgb2hex(selected_color)))


def save_assets() -> None:
    if palettes == {}:
        tk.messagebox.showerror("No Asset Set", "You must create an asset set before saving!")
        return
    if material_id.get() == "" or material_id.get().isspace():
        tk.messagebox.showerror("Error", "No material ID given")
        return
    if save_tool.get() == save_armor.get() == save_fluid.get() == 0:
        tk.messagebox.showerror("Error", "No save checkboxes clicked!")
        return
    if save_tool.get() == 1:
        if palettes["Tool"] == default_palettes["Tool"]:
            if not tk.messagebox.askokcancel("No Edits", "The 'Tool' palette is the same as the default palette! Did you mean to save it?"):
                return
        count = len(default_palettes["Tool"])
        image = Image.new("RGBA", (count, 1))
        for i in range(count):
            r, g, b = palettes["Tool"][i]
            image.putpixel((i, 0), (r, g, b, 255))
        image.save(Path(__file__) / "{0}/{1}.png".format(tool_palette_save_location, material_id.get()))
    if save_armor.get() == 1:
        if palettes["Armor"] == default_palettes["Armor"]:
            if not tk.messagebox.askokcancel("No Edits", "The 'Armor' palette is the same as the default palette! Did you mean to save it?"):
                return
        count = len(default_palettes["Armor"])
        image = Image.new("RGBA", (count, 1))
        for i in range(count):
            r, g, b = palettes["Armor"][i]
            image.putpixel((i, 0), (r, g, b, 255))
        image.save(Path(__file__) / "{0}/{1}.png".format(armor_palette_save_location, material_id.get()))
    if save_fluid.get() == 1:
        if palettes["Fluid"] == default_palettes["Fluid"]:
            if not tk.messagebox.askokcancel("No Edits", "The 'Fluid' palette is the same as the default palette! Did you mean to save it?"):
                return
        fluid_mapping = Mapping(Mapping.FLUID_IMAGE_TO_PALETTE)
        bucket_mapping = Mapping(Mapping.BUCKET_IMAGE_TO_PALETTE)

        # save images
        image_still = Image.open(Path(__file__).parent / "resources" / "fluid" / "still.png").convert("RGBA")
        image_flow = Image.open(Path(__file__).parent / "resources" / "fluid" / "flow.png").convert("RGBA")
        image_bucket = Image.open(Path(__file__).parent / "resources" / "fluid" / "bucket.png").convert("RGBA")
        replace_and_save(image_still, Path(__file__) / "{0}/molten_{1}_still.png".format(fluid_texture_save_location, material_id.get()), fluid_mapping, "Fluid")
        replace_and_save(image_flow, Path(__file__) / "{0}/molten_{1}_flow.png".format(fluid_texture_save_location, material_id.get()), fluid_mapping, "Fluid")
        replace_and_save(image_bucket, Path(__file__) / "{0}/molten_{1}_bucket.png".format(bucket_texture_save_location, material_id.get()), bucket_mapping, "Fluid")

        # save mcmeta files
        with open(Path(__file__).parent / "resources" / "fluid" / "still.mcmeta", "r") as source:
            with open(Path(__file__) / "{0}/molten_{1}_still.png.mcmeta".format(fluid_texture_save_location, material_id.get()), "w") as destination:
                destination.write(source.read())
        with open(Path(__file__).parent / "resources" / "fluid" / "flow.mcmeta", "r") as source:
            with open(Path(__file__) / "{0}/molten_{1}_flow.png.mcmeta".format(fluid_texture_save_location, material_id.get()), "w") as destination:
                destination.write(source.read())

        # update atlas (if enabled)
        if should_update_atlas:
            with open(Path(__file__) / atlas_save_location, "r+") as atlas:
                data1 = {"type": "single", "resource": "{0}:fluid/molten_{1}_flow".format(mod_id, material_id.get())}
                data2 = {"type": "single", "resource": "{0}:fluid/molten_{1}_still".format(mod_id, material_id.get())}
                data = json.load(atlas)
                data["sources"].append(data1)
                data["sources"].append(data2)
                atlas.seek(0)
                json.dump(data, atlas, indent=4)
    tk.messagebox.showinfo("Successfully Saved!", "Successfully saved asset set '{0}'!".format(material_id.get()))


def replace_and_save(image: Image.Image, save_path: Path, mapping: Mapping, palette_type: str) -> None:
    for x in range(image.width):
        for y in range(image.height):
            r, g, b, a = image.getpixel((x, y))
            if a == 0:
                image.putpixel((x, y), (r, g, b, a))
            else:
                index = mapping.map((r, g, b))
                if (index == -1):
                    image.putpixel((x, y), (r, g, b, a))
                else:
                    r_f, g_f, b_f = palettes[palette_type][index]
                    image.putpixel((x, y), (r_f, g_f, b_f, a))
    image.save(save_path)


def main():
    global tool_palette_save_location
    global armor_palette_save_location
    global fluid_texture_save_location
    global bucket_texture_save_location
    global should_update_atlas
    global atlas_save_location
    global mod_id
    with open("config.toml", "rb") as f:
        config = tomllib.load(f)
        tool_palette_save_location = config["output"]["tool_palette_save_location"]
        armor_palette_save_location = config["output"]["armor_palette_save_location"]
        fluid_texture_save_location = config["output"]["fluid_texture_save_location"]
        bucket_texture_save_location = config["output"]["bucket_texture_save_location"]
        should_update_atlas = config["output"]["atlas"]["update_atlas_on_fluid_save"]
        atlas_save_location = config["output"]["atlas"]["atlas_save_location"]
        mod_id = config["output"]["atlas"]["mod_id"]

    init_tool_images()
    init_armor_images()
    init_fluid_images()

    global selected_reference
    selected_reference = "<none>"

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
