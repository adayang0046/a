package z1final;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;
import java.util.List;

public class Agent {
    private int status; // 0 = S, 1 = I, 2 = R
    private double beta, gamma;

    public Agent(int status, double beta, double gamma) {
        this.status = status;
        this.beta = beta;
        this.gamma = gamma;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ScheduledMethod(start = 1, interval = 1)
    public void step() {
        // Infect others
        if (this.status == 1) {
            Context<Object> context = ContextUtils.getContext(this);
            for (Object obj : context) {
                if (obj instanceof Agent) {
                    Agent other = (Agent) obj;
                    if (other.getStatus() == 0 && Math.random() < this.beta) {
                        other.setStatus(1);
                    }
                }
            }
        }

        // Recover
        if (this.status == 1 && Math.random() < this.gamma) {
            this.status = 2;
        }
    }
}
