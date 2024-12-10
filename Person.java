package epidemic;

import java.util.List;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.SimUtilities;

public class Person {

    public enum Status {
        SUSCEPTIBLE, INFECTED, RECOVERED
    }

    private ContinuousSpace<Object> space;
    private Grid<Object> grid;
    private Status status;
    private boolean allowRecovery; // Flag to toggle SI or SIR behavior

    public Person(ContinuousSpace<Object> space, Grid<Object> grid, boolean allowRecovery) {
        this.space = space;
        this.grid = grid;
        this.status = Status.SUSCEPTIBLE;
        this.allowRecovery = allowRecovery;
    }

    @ScheduledMethod(start = 1, interval = 1)
    public void step() {
        // Infection spread logic
        if (status == Status.INFECTED) {
            infectNeighbors();
            if (allowRecovery) {
                recover();
            }
        }

        // Movement logic (optional)
        moveRandomly();
    }

    private void infectNeighbors() {
        // Get the grid location of this person
        GridPoint pt = grid.getLocation(this);

        // Use GridCellNgh to get neighboring cells
        GridCellNgh<Person> nghCreator = new GridCellNgh<>(grid, pt, Person.class, 1, 1);
        List<GridCell<Person>> gridCells = nghCreator.getNeighborhood(true);
        SimUtilities.shuffle(gridCells, RandomHelper.getUniform());

        // Attempt to infect susceptible neighbors
        for (GridCell<Person> cell : gridCells) {
            for (Object obj : grid.getObjectsAt(cell.getPoint().getX(), cell.getPoint().getY())) {
                if (obj instanceof Person) {
                    Person neighbor = (Person) obj;
                    if (neighbor.status == Status.SUSCEPTIBLE) {
                        if (Math.random() < 0.3) { // Infection probability
                            neighbor.status = Status.INFECTED;
                        }
                    }
                }
            }
        }
    }

    private void recover() {
        if (Math.random() < 0.1) { // Recovery probability
            status = Status.RECOVERED;
        }
    }

    public void moveRandomly() {
        GridPoint pt = grid.getLocation(this);
        NdPoint myPoint = space.getLocation(this);
        double angle = RandomHelper.nextDoubleFromTo(0, 2 * Math.PI);
        space.moveByVector(this, 1, angle, 0);

        // Update grid location
        myPoint = space.getLocation(this);
        grid.moveTo(this, (int) myPoint.getX(), (int) myPoint.getY());
    }

    // Getter and Setter for status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

