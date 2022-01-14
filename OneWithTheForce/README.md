# One With The Force

## Synopsis
One With The Force is a 2D, action-platformer game set in the Star Wars Universe.

## Motivation
I built this project in order to push myself as a programmer and game designer.

## How to Run
The game requires Main.java, Saber.java, LevelData.java, and every file in the Resources folder to run properly. Main.java is the file that needs to be run.
<Insert image here>
  
## Code Example
This code snippet determines what happens to an enemy when the saber collides with them.
```
for (Node stormtrooper : stormtroopers) {
	if (saberBox.getBoundsInParent().intersects(stormtrooper.getBoundsInParent()) && isSwinging) {
		try {
			MediaPlayer clash1 = new MediaPlayer(selectedSaber.getClash1());
			MediaPlayer clash2 = new MediaPlayer(selectedSaber.getClash2());
			MediaPlayer clash3 = new MediaPlayer(selectedSaber.getClash3());
					
			int random = 1 + (int)(Math.random() * ((3 -1) + 1));
			switch (random) {
				case 1:
					clash1.play();
					break;
				case 2:
					clash2.play();
					break;
				case 3:
					clash3.play();
					break;
			}
			stormtroopers.remove(stormtrooper);
			gameRoot.getChildren().remove(stormtrooper);
					
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
}
```
