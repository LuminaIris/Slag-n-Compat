import tkinter as tk

root: tk.Tk
main_frame: tk.Frame
top_frame: tk.Frame
builder_frame: tk.Frame

palettes = {
    "fluid": [],
    "tool": [],
    "armor": []
}

top_height = 0.13

def create_top_frame(parent: tk.Frame) -> tk.Frame:
    frame = tk.Frame(parent, highlightbackground="black", highlightcolor="black", highlightthickness=1)
    frame.place(relwidth=1, relheight=top_height)

    new_asset_button = tk.Button(frame, text = "New Asset Set", command = new_asset_button_pressed)
    new_asset_button.place(relx=0.05, rely=0.2, relheight=0.6, relwidth=0.185)

    material_id_label = tk.Label(frame, text = "Material ID")
    material_id_label.place(relx=0.28, rely=0.2, relheight=0.6, relwidth=0.07)
    material_id_field = tk.Entry(frame)
    material_id_field.place(relx=0.35, rely=0.2, relheight=0.6, relwidth=0.2)
    return frame

def create_palette_builder(parent: tk.Frame) -> tk.Frame:
    frame = init_frame(parent)

    return frame

def create_empty_builder(parent: tk.Frame) -> tk.Frame:
    frame = init_frame(parent)
    label = tk.Label(frame, text="Create a new asset set to begin", font=("Arial", 20))
    label.pack(side="top", fill="both", expand=True)

    return frame

def init_frame(parent: tk.Frame) -> tk.Frame:
    frame = tk.Frame(parent, highlightbackground="black", highlightcolor="black", highlightthickness=1)
    frame.place(relwidth=1, relheight=1-top_height, rely=top_height)
    return frame

def new_asset_button_pressed() -> None:
    global builder_frame
    builder_frame.destroy()
    builder_frame = create_palette_builder(main_frame)


def main():
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

