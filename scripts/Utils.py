from tkinter import Button

class PaletteButton(Button):
    def __init__(self, master=None, index: int = -1, palette_type: str = "", **kw):
        self.index: int = index
        self.palette_type: str = palette_type
        Button.__init__(self, master, {}, **kw)

class PreviewButton(Button):
    def __init__(self, master=None, id: int = -1, **kw):
        self.id: int = id
        Button.__init__(self, master, {}, **kw)

class Mapping:

    TOOL_PALETTE: str = "tool_palette"
    ARMOR_PALETTE: str = "armor_palette"
    FLUID_PALETTE: str = "fluid_palette"
    TOOL_IMAGE: str = "tool_image"
    ARMOR_IMAGE: str = "armor_image"
    FLUID_IMAGE: str = "fluid_image"
    BUCKET_IMAGE: str = "bucket_image"

    def __init__(self, start: str, end: str) -> None:
        self.start: str = start
        self.end: str = end