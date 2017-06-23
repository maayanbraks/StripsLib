package stripsLib;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Strips implements Planner {

	@Override
	public List<PlannableAction> plan(Plannable plannable) {
		if (plannable == null) {
			System.out.println("STRIPS - plannable is null");
			return null;
		}

		Stack<Predicate> stack = new Stack<>();
		LinkedList<PlannableAction> plan = new LinkedList<>();
		stack.push(plannable.getGoal());
		Predicate top = null;

		while (!(stack.isEmpty())) {
			top = stack.peek();
			if (!(top instanceof PlannableAction)) {
				if (!plannable.getKnowledgebase().satisfies(top))// unsatisfied
				{
					if (top instanceof Clause)// multiple and
												// unsatisfied
					{
						Clause c = (Clause) top;
						for (Predicate p : c.getPredicates())
							stack.push(p);
					} else// single and unsatisfied
					{
						stack.pop();
						Set<PlannableAction> actions = plannable.getsatisfyingActions(top);
						if (actions != null) {
							for (PlannableAction a : actions) {
								stack.push(a);
								stack.push(a.getPreConditions());
							}
						} else {
							if (stack.size() > 1)// there is another predicate
							{
								System.out.println("This path not good, lets go from another one!");
								stack.pop();
								Predicate p = stack.pop();
								stack.push(top);
							} else {
								System.out.println("STRIPS - can't solve this level...");
								return null;
							}
						}
					}
				} else {
					stack.pop();
				}
			} else// top is action
			{
				stack.pop();
				PlannableAction action = (PlannableAction) top;
				plannable.getKnowledgebase().updated(action.getEffects());
				plan.addLast(action);
			}
		}

		return plan;
	}

}
