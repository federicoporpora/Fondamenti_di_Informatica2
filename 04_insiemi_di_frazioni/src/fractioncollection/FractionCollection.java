package fractioncollection;
import frazione.Frazione;

public class FractionCollection {
	private static final int DEFAULT_GROWTH_FACTOR = 2;
	private static final int DEFAULT_PHYSICAL_SIZE = 10;
	private Frazione[] innerContainer;
	private int size;
	public FractionCollection() {
		innerContainer = new Frazione[DEFAULT_PHYSICAL_SIZE];
		size = 0;
	}
	public FractionCollection(int physicalSize) {
		innerContainer = new Frazione[physicalSize];
		size = 0;
	}
	public FractionCollection(Frazione[] collection) {
		innerContainer = new Frazione[collection.length];
		size = 0;
		for (Frazione frazione : collection) {
			if (frazione != null) {
				innerContainer[size] = frazione;
				size++;
			}
		}
	}
	public Frazione get(int index) { return (innerContainer[index]); }
	public int size() { return size; }
	public void put(Frazione f) {
		if (size < innerContainer.length) {
			innerContainer[size] = f;
			size++;
		}
		else {
			FractionCollection res = new FractionCollection(this.innerContainer.length * DEFAULT_GROWTH_FACTOR);
			for (int i = 0; i < size; i++) {
				if (this.innerContainer[i] != null) {
					res.innerContainer[i] = this.innerContainer[i];
				}
			}
			res.innerContainer[size] = f;
			size++;
			innerContainer = res.innerContainer;
		}
	}
	public void remove(int index) {
		if (index == size - 1) { innerContainer[index] = null; size--; }
		else {
			for (int i = index; i < size - 1; i++) {
				innerContainer[i] = innerContainer[i + 1];
			}
			innerContainer[size - 1] = null;
			size--;
		}
	}
	public String toString() {
		 java.util.StringJoiner res = new java.util.StringJoiner(", ", "[", "]");
		 for (int k = 0; k < innerContainer.length && innerContainer[k]!= null; k++){
			 res.add(innerContainer[k].toString());
		 }
		 return res.toString();
	}
	public FractionCollection sum(FractionCollection collection) {
		if (this.size != collection.size) return null;
		FractionCollection result = new FractionCollection(size);
		for (int k=0; k < result.size; k++){
			result.innerContainer[k] = this.innerContainer[k].sum(collection.innerContainer[k]);
		}
		return result;
    }
	public FractionCollection mul(FractionCollection collection) {
		if (this.size!= collection.size) return null;
        FractionCollection result = new FractionCollection(size);
        for (int k=0; k < result.size; k++){
            result.innerContainer[k] = this.innerContainer[k].mul(collection.innerContainer[k]);
        }
        return result;
	}
	public FractionCollection sub(FractionCollection collection) {
		if (this.size!= collection.size) return null;
        FractionCollection result = new FractionCollection(size);
        for (int k=0; k < result.size; k++){
            result.innerContainer[k] = this.innerContainer[k].sub(collection.innerContainer[k]);
        }
        return result;
	}
	public FractionCollection div(FractionCollection collection) {
		if (this.size!= collection.size) return null;
        FractionCollection result = new FractionCollection(size);
        for (int k=0; k < result.size; k++){
            result.innerContainer[k] = this.innerContainer[k].div(collection.innerContainer[k]);
        }
        return result;
	}
}