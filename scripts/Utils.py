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
        if self.mapping_type == self.ARMOR_IMAGE_TO_PALETTE:
            match color:
                case (224, 224, 224): return 0
                case (198, 198, 198): return 1
                case (170, 170, 170): return 2
                case (142, 142, 142): return 3
                case (113, 113, 113): return 4
                case (85, 85, 85): return 5
                case (57, 57, 57): return 6
                case (29, 29, 29): return 7
                case (0, 0, 0): return 8
            return -1
        if self.mapping_type == self.FLUID_IMAGE_TO_PALETTE:
            match color:
                case (244, 244, 244): return 0
                case (230, 230, 230): return 1
                case (211, 211, 211): return 2
                case (192, 192, 192): return 3
                case (174, 174, 174): return 4
                case (158, 158, 158): return 5
                case (136, 136, 136): return 6
                case (121, 121, 121): return 7
                case (105, 105, 105): return 8
                case (90, 90, 90): return 9
            return -1
        if self.mapping_type == self.BUCKET_IMAGE_TO_PALETTE:
            match color:
                case (255, 255, 253): return 0
                case (247, 240, 232): return 1
                case (235, 228, 213): return 3
                case (213, 204, 186): return 5
                case (190, 179, 162): return 6
            return -1
        else:
            print("Unknown mapping type")
            return -1
