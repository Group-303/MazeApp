public enum Direction {
    NORTH (1, 0, -1),
    EAST (2, 1, 0),
    SOUTH (4, 0, 1),
    WEST (8, -1, 0);
    private int bit;
    private int dx;
    private int dy;
    public Direction opposite;

    static {
        NORTH.opposite = SOUTH;
        EAST.opposite = WEST;
        SOUTH.opposite = NORTH;
        WEST.opposite = EAST;
    }

    Direction(int bit, int dx, int dy)
    {
        this.bit = bit;
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return this.dx;
    }

    public int getDy() {
        return this.dy;
    }

    public int getBit() {
        return this.bit;
    }
}
