Slag n' Embers Asset Creator
================

What Even is This
----------------

Heya, Lumina Iris here. When I started working on Slag n' Compat, I got super annoyed when I was creating the assets for my first material (Mekanism's refined obsidian)

I had drawn up my palette, basing it off of the refined obsidian ingot, I got into game, and it looked nothing like the tools. It was really annoying >_<

So, I had an idea that would make it a little easier to make all the assets I would need to make for this project. The biggest problem was simply that I had no way to translate a color palette into what it would look like in-game, which is what the asset creator is meant to solve. It's a little clunky, but it gets the job done (at least for me)!

Using the Asset Creator
-----------------

To use the asset creator, simply run the AssetCreator.py script. Idk how to bundle pip libraries with python, so you'll need to have these libraries installed:
- pillow
- qtpy
- vcolorpicker

Once you start the asset creator, you'll need to make a new asset set. An asset set consists of 3 things: a tool palette, an armor palette, and a fluid palette. At the top of the creator, you can set the material id and which assets you want to save. 

On the left, you'll see the palette preview, and on the right you can upload reference images. Change your selected color by clicking the color button right of the palette, which will open a color picker, or by clicking any pixel on a reference image, which copies that pixel's color. To change the palette, click either the palette color or the preview image; both will update the palette. You can also change the preview using the dropdowns at the bottom of the preview side, changing the tool/armor layer/fluid image and texture style.

Once you're done, click the save button and the outputs will be saved to the configured output locations (see configuration below). Tool and armor palettes will be saved as a palette (which is what SnE uses). Fluid palettes will be converted into still, flowing, and bucket images, with the palette being mapped to a template.

Configuring the Asset Creator
-----------------

The asset creator can be configured with the config.toml file and the resources folder.
- To add new modular tools to asset creator, update the tool_types list in the config, and add the textures to the resources folder. Similar story for armor layers, fluid types, and texture styles.
- To change the save locations, edit the values in the config. The values are relative to AssetCreator.py file, so if you need to go backwards, add '../' before your file path. The default config uploads to the slagcompat asset folders.
  - There's an extra option for editing the block atlas values, which is needed for the fluid textures to show in-game, if you don't need this, it can be turned off in the config.


Thanks for using the asset creator! 
-----------------

I'm not the best app creator, so if you wanna contribute the asset creator, feel free to open an issue and/or make a pr!