package stripsLib;

import java.util.List;

public interface Planner
{
	public List<PlannableAction> plan(Plannable plannable);

}
