package stripsLib;

import java.util.Set;

public interface Plannable {
	Clause getGoal();

	Clause getKnowledgebase();

	Set<PlannableAction> getsatisfyingActions(Predicate top);

}
