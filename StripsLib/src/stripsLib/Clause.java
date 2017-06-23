package stripsLib;

import java.util.HashSet;

public class Clause extends Predicate {

	private HashSet<Predicate> predicates;

	public Clause(Predicate... predicates) {
		super("And", "", "");
		this.predicates = new HashSet<>();
		if (predicates != null) {
			for (Predicate p : predicates) {
				this.predicates.add(p);
			}
			updateDescription();
		}
	}

	public HashSet<Predicate> getPredicates() {
		return this.predicates;
	}

	@Override
	public boolean contradicts(Predicate predicate) {
		if (predicate instanceof Clause)
			return contradictsClause((Clause) predicate);
		else {
			for (Predicate p : this.predicates) {
				if (p.contradicts(predicate))
					return true;
			}
			return false;
		}

	}

	@Override
	public boolean satisfies(Predicate predicate) {
		if (predicate instanceof Clause)
			return satisfiesClause((Clause) predicate);
		else {
			for (Predicate p : this.predicates) {
				if (p.satisfies(predicate))
					return true;
			}
			return false;
		}
	}

	@Override
	public boolean contradictsClause(Clause clause) {
		for (Predicate p : clause.getPredicates()) {
			if (!contradicts(p))
				return false;
		}
		return true;

	}

	@Override
	public boolean satisfiesClause(Clause clause) {
		for (Predicate p : clause.getPredicates()) {
			if (!satisfies(p))
				return false;
		}
		return true;
	}

	// Updates METHODS:
	public void updated(Predicate effect) {
		HashSet<Predicate> removeablePredicates = new HashSet<>();
		if (this.predicates.size() > 0) {
			for (Predicate p : this.predicates) {
				if (p != null) {
					if (effect instanceof Clause) {
						if (p.contradictsClause((Clause) effect))

							removeablePredicates.add(p);
					} else {

						if (p.contradicts(effect))

							removeablePredicates.add(p);
					}
				}
			}
			this.predicates.removeAll(removeablePredicates);
			if (effect instanceof Clause)
				updateClause((Clause) effect);
			else
				this.predicates.add(effect);
			updateDescription();
		} else {
			this.predicates.add(effect);

			updateDescription();
		}

	}

	private void updateClause(Clause clause) {
		for (Predicate p : clause.getPredicates()) {
			if (p instanceof Clause) {
				updateClause((Clause) p);
			} else
				this.predicates.add(p);
		}
	}

	private void updateDescription() {
		value = "{";
		for (Predicate p : this.predicates) {
			if (p != null) {
				this.predicates.add(p);
				value += p.toString() + " -AND- ";
			}
		}
		value += "}";
	}
}
