package stripsLib;

public class Predicate {
	protected String type, id, value;

	public Predicate(String type, String id, String value) {
		super();
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public boolean contradicts(Predicate p) {
		if (p instanceof Clause)
			return contradictsClause((Clause) p);
		else
			return type.equals(p.type) && id.equals(p.id) && !value.equals(p.value);

	}

	public boolean satisfies(Predicate p) {
		if (p instanceof Clause)
			return satisfiesClause((Clause) p);
		else
			return (type.equals(p.type) && (id.equals(p.id) || p.id.equals("?")) && value.equals(p.value));
	}

	public boolean contradictsClause(Clause c) {
		for (Predicate p : c.getPredicates()) {
			if (contradicts(p))
				return true;
		}
		return false;

	}

	public boolean satisfiesClause(Clause c)

	{
		for (Predicate p : c.getPredicates()) {
			if (satisfies(p))
				return true;
		}
		return false;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean equals(Predicate p) {
		return type.equals(p.type) && id.equals(p.id) && value.equals(p.value);
	}

	@Override
	public int hashCode() {
		return (type + id + value).hashCode();
	}

	@Override
	public String toString() {
		return type + "_" + id + "_" + value;
	}

}
