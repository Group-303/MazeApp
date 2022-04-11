# MazeApp

Client Project Description
- Software tool used by designers to increase productivity at creating mazs, automating repetitive and less creative parts of process
- Allow designers to demonstrate their craft
- MainGUI software that helps designers create custom mazes

- Maze creation
    - Drawing
    - Randomly generated maze
    - Maze verification
    - Produce a solution
    - Allow logos or other images to be on the maze
- Maze storage
    - Stored in database
    - Retrieval of existing mazes from DB
- Support existing mazes
    - Convert to image file
    - Export mazes with solution (different coloured line showing path from entrance to exit)
    - Simplistic MainGUI

User Stories
Generation
- Built by designer
- Built by algorithm
    - Supports editing
- Should resemble a maze (multiple paths, one entrance/exit, etc.)
- Must be solvable
- Supports adding images
    - Company logo
    - No fixed size
    - Walled off from the rest of the maze
    - Can be placed manually
    - Can be automatically added
- Maximum size is 100x100 cells
- Design types
    - Open Entrance and Exit Style
        - Two lines are missing
        - Arrows point into the entrance and out the exit
        - Entrance through the top left, exit through bottom right ()
    - Image Entrance and Exit Style
        - Full border around maze
        - Images represent the entrance and exit
        - Similar to logo adding, but part of the maze

Database
- Information for each maze
    - Name of the maze
    - Author of the maze
    - Date and time of creation of the maze
    - Date and time of last edit of the maze
- Able to be sorted
- Date and time fields input automatically
- Sotredi n MariaDB, PostgreSQL, or SQLite3 database on the server
- Editable properies file (called db.props) with format similar to:
jdbc.url=jdbc:mariadb://localhost:3306
jdbc.schema=mazeco
jdbc.username=user
jdbc.password=hdsajkhd

- Should be able to move to different machine and change password without software recompile
- Assume that such user will be created
- Tables for the DB must be created by the software on startup

Solutions
- Automatic solving
- Information reported about the difficulty of the maze
    - Percentage of cells in a maze reached by an optimal solution of the maze
        - 100% would mean you need to go through every single cell
        - Lower percentages indicate more dead ends
    - Percentage of cells in the maze that are dead ends (walls in 3/4 directions)
- Show optimal solution with a coloured line through the maze
    - Toggleable

Exporting
- Browse list of mazes
- Select multiple mazes for image export
- Prompt for folder
- Filled with image files for each of the mazes
- Toggle solution shown in image
- Image output aspect ratios
    - 6 column 4 row maze should output a 384x256 image (64x64 per cell)
    - 100 column 100 row maze should output a 1600x1600 image (16x16 per cell)
