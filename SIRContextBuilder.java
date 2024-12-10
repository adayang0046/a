package z1final;
import repast.simphony.context.DefaultContext;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.parameter.Parameters;
import repast.simphony.space.projection.Projection;
import repast.simphony.util.collections.IndexedIterable;
import repast.simphony.valueLayer.ValueLayer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import repast.simphony.context.Context;
import repast.simphony.context.ContextEvent;
import repast.simphony.context.ContextListener;


public class SIRContextBuilder extends DefaultContext<Object> {
	@Override
    public Context<Object> build(Context<Object> context) {
        context.setId("SIRModel");

        // Get parameters from the simulation
        Parameters params = RunEnvironment.getInstance().getParameters();
        int population = params.getInteger("population");
        double beta = params.getDouble("beta");
        double gamma = params.getDouble("gamma");
        int initialInfected = params.getInteger("initialInfected");

        // Add agents to the context
        for (int i = 0; i < population; i++) {
            if (i < initialInfected) {
                context.add(new Agent(1, beta, gamma));
            } else {
                context.add(new Agent(0, beta, gamma));
            }
        }

        // Create schedule
        RunEnvironment.getInstance().endAt(params.getInteger("endTick"));
        return context;
    }

	@Override
	public <T> T[] toArray(IntFunction<T[]> generator) {
		// TODO Auto-generated method stub
		return super.toArray(generator);
	}

	@Override
	public boolean removeIf(Predicate<? super Object> filter) {
		// TODO Auto-generated method stub
		return super.removeIf(filter);
	}

	@Override
	public Spliterator<Object> spliterator() {
		// TODO Auto-generated method stub
		return super.spliterator();
	}

	@Override
	public Stream<Object> stream() {
		// TODO Auto-generated method stub
		return super.stream();
	}

	@Override
	public Stream<Object> parallelStream() {
		// TODO Auto-generated method stub
		return super.parallelStream();
	}

	@Override
	public void forEach(Consumer<? super Object> action) {
		// TODO Auto-generated method stub
		super.forEach(action);
	}

	@Override
	protected boolean addInternal(Object o) {
		// TODO Auto-generated method stub
		return super.addInternal(o);
	}

	@Override
	protected Iterator<Object> iteratorInternal() {
		// TODO Auto-generated method stub
		return super.iteratorInternal();
	}

	@Override
	protected boolean removeInternal(Object o) {
		// TODO Auto-generated method stub
		return super.removeInternal(o);
	}

	@Override
	protected boolean containsInternal(Object o) {
		// TODO Auto-generated method stub
		return super.containsInternal(o);
	}

	@Override
	protected int sizeInternal() {
		// TODO Auto-generated method stub
		return super.sizeInternal();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return super.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return super.retainAll(c);
	}

	@Override
	public IndexedIterable<Object> getObjects(Class<?> clazz) {
		// TODO Auto-generated method stub
		return super.getObjects(clazz);
	}

	@Override
	public Stream<Object> getObjectsAsStream(Class<?> clazz) {
		// TODO Auto-generated method stub
		return super.getObjectsAsStream(clazz);
	}

	@Override
	public Stream<Object> getRandomObjectsAsStream(Class<? extends Object> clazz, long count) {
		// TODO Auto-generated method stub
		return super.getRandomObjectsAsStream(clazz, count);
	}

	@Override
	public Iterable<Object> getRandomObjects(Class<? extends Object> clazz, long count) {
		// TODO Auto-generated method stub
		return super.getRandomObjects(clazz, count);
	}

	@Override
	public Object getRandomObject() {
		// TODO Auto-generated method stub
		return super.getRandomObject();
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(Object id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public Object getTypeID() {
		// TODO Auto-generated method stub
		return super.getTypeID();
	}

	@Override
	public void setTypeID(Object id) {
		// TODO Auto-generated method stub
		super.setTypeID(id);
	}

	@Override
	public void addContextListener(ContextListener<Object> listener) {
		// TODO Auto-generated method stub
		super.addContextListener(listener);
	}

	@Override
	public Collection<ContextListener<Object>> getContextListeners() {
		// TODO Auto-generated method stub
		return super.getContextListeners();
	}

	@Override
	public Iterable<Object> query(org.apache.commons.collections15.Predicate query) {
		// TODO Auto-generated method stub
		return super.query(query);
	}

	@Override
	public void removeContextListener(ContextListener<Object> listener) {
		// TODO Auto-generated method stub
		super.removeContextListener(listener);
	}

	@Override
	protected void fireAddContextEvent(Object o) {
		// TODO Auto-generated method stub
		super.fireAddContextEvent(o);
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return super.contains(o);
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return super.iterator();
	}

	@Override
	public void eventOccured(ContextEvent ev) {
		// TODO Auto-generated method stub
		super.eventOccured(ev);
	}

	@Override
	public void addSubContext(Context<? extends Object> context) {
		// TODO Auto-generated method stub
		super.addSubContext(context);
	}

	@Override
	public boolean hasSubContext() {
		// TODO Auto-generated method stub
		return super.hasSubContext();
	}

	@Override
	public Iterable<Context<? extends Object>> getSubContexts() {
		// TODO Auto-generated method stub
		return super.getSubContexts();
	}

	@Override
	public void removeSubContext(Context<? extends Object> context) {
		// TODO Auto-generated method stub
		super.removeSubContext(context);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		super.clear();
	}

	@Override
	protected boolean handleRemove(Object o) {
		// TODO Auto-generated method stub
		return super.handleRemove(o);
	}

	@Override
	protected void fireRemoveEvent(Object o) {
		// TODO Auto-generated method stub
		super.fireRemoveEvent(o);
	}

	@Override
	protected void fireSubContextAdded(Context<? extends Object> context) {
		// TODO Auto-generated method stub
		super.fireSubContextAdded(context);
	}

	@Override
	protected void fireSubContextRemoved(Context<? extends Object> context) {
		// TODO Auto-generated method stub
		super.fireSubContextRemoved(context);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return super.size();
	}

	@Override
	public Context<? extends Object> getSubContext(Object id) {
		// TODO Auto-generated method stub
		return super.getSubContext(id);
	}

	@Override
	public Context findParent(Object o) {
		// TODO Auto-generated method stub
		return super.findParent(o);
	}

	@Override
	public Context findContext(Object id) {
		// TODO Auto-generated method stub
		return super.findContext(id);
	}

	@Override
	public Iterable<Class> getAgentTypes() {
		// TODO Auto-generated method stub
		return super.getAgentTypes();
	}

	@Override
	public Iterable<Object> getAgentLayer(Class<Object> agentType) {
		// TODO Auto-generated method stub
		return super.getAgentLayer(agentType);
	}

	@Override
	public <X extends Projection<?>> X getProjection(Class<X> projection, String name) {
		// TODO Auto-generated method stub
		return super.getProjection(projection, name);
	}

	@Override
	public <X extends Projection<?>> Iterable<X> getProjections(Class<X> clazz) {
		// TODO Auto-generated method stub
		return super.getProjections(clazz);
	}

	@Override
	public Projection<?> getProjection(String name) {
		// TODO Auto-generated method stub
		return super.getProjection(name);
	}

	@Override
	public void addProjection(Projection<? super Object> projection) {
		// TODO Auto-generated method stub
		super.addProjection(projection);
	}

	@Override
	public Projection<? super Object> removeProjection(String projectionName) {
		// TODO Auto-generated method stub
		return super.removeProjection(projectionName);
	}

	@Override
	public Collection<Projection<?>> getProjections() {
		// TODO Auto-generated method stub
		return super.getProjections();
	}

	@Override
	public void addValueLayer(ValueLayer valueLayer) {
		// TODO Auto-generated method stub
		super.addValueLayer(valueLayer);
	}

	@Override
	public ValueLayer removeValueLayer(String name) {
		// TODO Auto-generated method stub
		return super.removeValueLayer(name);
	}

	@Override
	public Collection<ValueLayer> getValueLayers() {
		// TODO Auto-generated method stub
		return super.getValueLayers();
	}

	@Override
	public ValueLayer getValueLayer(String name) {
		// TODO Auto-generated method stub
		return super.getValueLayer(name);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return super.isEmpty();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return super.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return super.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		// TODO Auto-generated method stub
		return super.addAll(c);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
