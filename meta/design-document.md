working title: horizon's dinner
## minimum-viable-product:
- MIT license publishable code
- pam's harvestcraft patching framework
	- remove/modify unwanted features
	- take over mods pre init, etc so we have potential full control
	- Where needed take over the logic of a Pam's item, block, recipe, etc. The mod shouldn't take credit for the item. It should still say "Pam's Harvestcraft" on the tooltip. potentially it could indicate that the item was patched by the mod.
- add system for own food items, recipes, logic, etc that interact with but are completely independent from pam's
## goals:
slowly replace pam's completely
make a balance pass over pam's foods
redesign/gregify weird water mechanics?
properly replace references to pam's from gtnh's side and write save migration code
## stretch-goals:
stronger balance pass
introduce food tiers & hunger resistance
healing axe lategame bauble replacement
## aspirational-goals:
more intuitive cooking (most ingredients should be combineable into a okay meal, good meals should be made from rare ingredients but ideally still not be incredibly pedantic)
interesting food cooking (soup, food tetris, food puzzle)

## non-goals for now:
nerfing the healing axe
other very big food balance changes

## points that need to be worked out
- Is there desire for integrating this mod (and the pam's patch) with crops nh?
- Is the mod supposed to work without gtnh?
- Is the gtnh vending machine a acceptable replacement for the pam's market?
