from tkinter import Button

class PaletteButton(Button):
    def __init__(self, master=None, index: int = -1, palette_type: str = "", **kw):
        self.index: int = index
        self.palette_type: str = palette_type
        Button.__init__(self, master, {}, **kw)
