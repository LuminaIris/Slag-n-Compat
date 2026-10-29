from tkinter import Button

class PaletteButton(Button):
    def __init__(self, master=None, index: int = -1, palette_type: str = "", **kw):
        self.index: int = index
        self.palette_type: str = palette_type
        Button.__init__(self, master, {}, **kw)

class PreviewButton(Button):
    def __init__(self, master=None, index: int = -1, **kw):
        self.index: int = index
        Button.__init__(self, master, {}, **kw)

class ReferenceButton(Button):
    def __init__(self, master=None, color: tuple[int, int, int] = (-1, -1, -1), **kw):
        self.color: tuple[int, int, int] = color
        Button.__init__(self, master, {}, **kw)

class Mapping:

    TOOL_IMAGE_TO_PALETTE: str = "tool"
    ARMOR_IMAGE_TO_PALETTE: str = "armor"
    FLUID_IMAGE_TO_PALETTE: str = "fluid"
    BUCKET_IMAGE_TO_PALETTE: str = "bucket"

    def __init__(self, mapping_type: str) -> None:
        self.mapping_type: str = mapping_type

    def map(self, color: tuple[int, int, int]) -> int:
        if self.mapping_type == self.TOOL_IMAGE_TO_PALETTE:
            match color:
                case (224, 224, 224): return 0
                case (192, 192, 192): return 1
                case (160, 160, 160): return 2
                case (128, 128, 128): return 3
                case (96, 96, 96): return 4
                case (64, 64, 64): return 5
                case (32, 32, 32): return 6
                case (0, 0, 0): return 7
            return -1
        else:
            print("Unknown mapping type")
            return -1
