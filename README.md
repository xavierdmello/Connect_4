# Connect_4
Over-engineered version of Connect 4.

# Features
- Player vs. Player or Player vs. CPU
- Customizable board dimensions
- Customazable amount of discs-in-a-row needed to win (ex: Connect 5, Connect 3)
- The title centers itself over the Connect 4 board. How cool is that?

# Instructions
Run Connect_4.java in /src/connect_4/

# AI
It chooses the best move based on a reward system: whichever move will give it the most `iceCream.`

It's not the best thing ever, but I wanted to think of an AI by myself - and I suceeded in that goal!

- It's modular, so you can add ways to make it better by giving the AI iceCream for a good move.
- It'll calculate the best move one step in the future.
- It will always win if it immediately can.
- It will block you if you try and pull a quick one, but it won't detect any setups.

Overall, it's probably like playing against a 7-year-old. See /src/connect_4/AI.java for more info.

# FAQ
Why is AI.java so nonsensical?
- Uh... just don't scroll down the document too far <(￣︶￣)>
