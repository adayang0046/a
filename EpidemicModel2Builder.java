package epidemic;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.continuous.ContinuousSpaceFactory;
import repast.simphony.context.space.continuous.ContinuousSpaceFactoryFinder;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.continuous.ContinuousSpace;
import repast.simphony.space.continuous.NdPoint;
import repast.simphony.space.continuous.RandomCartesianAdder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

public class EpidemicModel2Builder implements ContextBuilder<Object> {

    @Override
    public Context<Object> build(Context<Object> context) {
        context.setId("EpidemicModel2");

        // Create continuous space
        ContinuousSpaceFactory spaceFactory = ContinuousSpaceFactoryFinder.createContinuousSpaceFactory(null);
        ContinuousSpace<Object> space = spaceFactory.createContinuousSpace(
            "space", context,
            new RandomCartesianAdder<>(),
            new repast.simphony.space.continuous.WrapAroundBorders(),
            50, 50
        );

        // Create grid
        GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
        Grid<Object> grid = gridFactory.createGrid(
            "grid", context,
            new GridBuilderParameters<>(
                new WrapAroundBorders(),
                new SimpleGridAdder<>(),
                true, // Multilayer grid
                50,   // Grid width
                50    // Grid height
            )
        );

        // Retrieve parameters
        Parameters params = RunEnvironment.getInstance().getParameters();
        int susceptibleCount = (Integer) params.getValue("susceptible_count");
        int initialInfectedCount = (Integer) params.getValue("initial_infected_count");
        boolean useSIR = (Boolean) params.getValue("use_SIR");

        // Add susceptible individuals
        for (int i = 0; i < susceptibleCount; i++) {
            Person person = new Person(space, grid, useSIR);
            context.add(person);
        }

        // Add initial infected individuals
        for (int i = 0; i < initialInfectedCount; i++) {
            Infected infected = new Infected(space, grid, useSIR);
            context.add(infected);
        }

        // Align space and grid locations
        for (Object obj : context.getObjects(Object.class)) {
            NdPoint pt = space.getLocation(obj);
            grid.moveTo(obj, (int) pt.getX(), (int) pt.getY());
        }

        // Set simulation runtime
        if (RunEnvironment.getInstance().isBatch()) {
            RunEnvironment.getInstance().endAt(100); // Stop after 100 steps
        }

        return context;
    }
}

