package z1final;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;

public class DataCollector {

    @ScheduledMethod(start = 1, interval = 1)
    public void collectData() {
        Context<Object> context = ContextUtils.getContext(this);
        int susceptible = 0, infected = 0, recovered = 0;

        for (Object obj : context) {
            if (obj instanceof Agent) {
                Agent agent = (Agent) obj;
                switch (agent.getStatus()) {
                    case 0 -> susceptible++;
                    case 1 -> infected++;
                    case 2 -> recovered++;
                }
            }
        }

        System.out.printf("S = %d, I = %d, R = %d%n", susceptible, infected, recovered);
    }
}
