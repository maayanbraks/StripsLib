package stripsLib;

import java.util.LinkedList;

import searchLib.Action;

public class PlannableAction extends Predicate {

	private LinkedList<Action> subActions;
	private Clause preconditions, effects;
	private Action action;

	public PlannableAction(String type, String id, String value) {
		super(type, id, value);
		this.subActions = null;
		this.action = null;
		this.effects = null;
		this.preconditions = null;
	}

	public LinkedList<Action> getSubActions() {
		return subActions;
	}

	public void setSubActions(LinkedList<Action> subActions) {
		this.subActions = subActions;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Clause getPreConditions() {
		return preconditions;
	}

	public void setPreConditions(Clause preConditions) {
		this.preconditions = preConditions;
	}

	public Clause getEffects() {
		return effects;
	}

	public void setEffects(Clause effects) {
		this.effects = effects;
	}

}
