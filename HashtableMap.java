// --== CS400 Project One File Header ==--
// Name: Ziqi Liao
// CSL Username: zliao
// Email: zliao47@wisc.edu
// Lecture #: Section 3: MoWeFr 1:20PM - 2:10PM, Florian Heimerl
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

/**
 * This is a public class HashtableMap that implements the MapADT.
 * 
 * @author Ziqi Liao
 *
 * @param <KeyType>   generic type parameters of key
 * @param <ValueType> generic type parameters of value
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	protected LProbMapEntry<KeyType, ValueType>[] buckets;
	private int capacity;
	private int size;
	double LF; // Load factor

	/**
	 * Create a helper class to group together key-value pairs within a single
	 * object
	 * 
	 * @param <KeyType>   generic type of LProbMapEntry key
	 * @param <ValueType> generic type of LProbMapEntry value
	 */
	public class LProbMapEntry<KeyType, ValueType> {
		private KeyType k;
		private ValueType v;
		private Boolean occupied; // the remove status of a bucket

		// constructor
		public LProbMapEntry(KeyType k, ValueType v) {
			this.k = k;
			this.v = v;
			occupied = false; // the bucket has not been occupied before
		}

		public KeyType getK() {
			return k;
		}

		public ValueType getV() {
			return v;
		}

		public void setK(KeyType k) {
			this.k = k;
		}

		public void setV(ValueType v) {
			this.v = v;
		}
	}

	/**
	 * constructors of HashtableMap class
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		this.capacity = capacity;
		// instantiate the array using the raw type
		this.buckets = (LProbMapEntry<KeyType, ValueType>[]) new LProbMapEntry[capacity];
		this.size = 0;
		LF = 0.7;

	}

	/**
	 * constructors of HashtableMap class
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		this.capacity = 8; // with default capacity = 8
		// 1. Instantiate the array for your hashtable using the raw type:new
		// LProbMapEntry[capacity]
		// 2. Cast to the complete type: (LProbMapEntry<KeyType, ValueType>[])
		// 3. Storing this reference in a protected instance field "buckets = "
		this.buckets = (LProbMapEntry<KeyType, ValueType>[]) new LProbMapEntry[capacity];
		this.size = 0;
		LF = 0.7;
	}

	/**
	 * add a new key-value pair/mapping to this collection
	 */
	@Override
	public void put(KeyType key, ValueType value) throws IllegalArgumentException {
		int index = Math.abs(key.hashCode()) % capacity; // determine the initial bucket
		int bucketsProbed = 0; // the number if buckets being probed
//		System.out.println(key);
		if ((buckets[index] != null)&& buckets[index].getK().equals(key)|| key == null)
			throw new IllegalArgumentException("the key is null or has duplicate key");

		int i = index;

		while (bucketsProbed < capacity) {
			// if loadfactor > 0.7, resizing
			double LF = (double) (size + 1) / capacity;

			if (LF >= 0.7) {
				resize(2 * capacity);
			}

			if (buckets[i] == null || buckets[i].occupied == true) {
				buckets[i] = new LProbMapEntry<KeyType, ValueType>(key, value);
				size++;
				return;
			}
			i = (i + 1) % capacity;
			bucketsProbed++; // Increment number of buckets probed
		}

	}

	/**
	 * dynamically grow hashtable by doubling its capacity and rehashing
	 * 
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {

		LProbMapEntry<KeyType, ValueType>[] temp = buckets;

		buckets = (LProbMapEntry<KeyType, ValueType>[]) new LProbMapEntry[capacity];

		this.size = 0;

		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				put(temp[i].getK(), temp[i].getV());
			}
		}
		this.capacity = capacity;
	}

	/**
	 * check whether a key maps to a value within this collection
	 */
	@Override
	public boolean containsKey(KeyType key) {
		try {
			get(key);
		} catch (NoSuchElementException e) {
			return false;
		}

		return true;
	}

	/**
	 * Retrieve the specific value that a key maps to
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		int i = Math.abs(key.hashCode() ) % capacity; // determine the initial bucket
		int bucketsProbed = 0;

		while ((buckets[i] != null && buckets[i].getK() != null && !buckets[i].getK().equals(key)
				&& bucketsProbed < capacity) || // bucket not null and not the key
				(buckets[i] == null && bucketsProbed < capacity)) { // bucket is null and
																									// the bucket has
																									// been occupied,
																									// next
			i = (i + 1) % capacity;
			bucketsProbed++;
		}

		// get the key
		if (buckets[i] != null && buckets[i].getK().equals(key) && buckets[i].occupied == false) {
			ValueType value = buckets[i].getV();
			return value;
		}

		// throws exception when key is not stored in this collection
		throw new NoSuchElementException("key is not stored in this collection");
	}

	/**
	 * Remove the mapping for a given key from this collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ValueType remove(KeyType key) throws NoSuchElementException {
		int i =  Math.abs(key.hashCode()) % capacity; // determine the initial bucket
		int bucketsProbed = 0;

		while ((buckets[i] != null && buckets[i].getK() != null && !buckets[i].getK().equals(key)
				&& bucketsProbed < capacity) || // bucket not null and not the key
				(buckets[i] != null && buckets[i].occupied == true && bucketsProbed < capacity)) { // bucket is null and
																									// the bucket has
																									// been occupied,
																									// next
			i = (i + 1) % capacity;
			bucketsProbed++;
		}

		// get the key
		if (buckets[i] != null && buckets[i].getK().equals(key) && buckets[i].occupied == false) {
			ValueType value = buckets[i].getV();
			buckets[i].occupied = true; // sentinel value set to true

			size--;
			return value;
		}
		// throws exception when key is not stored in this collection
		throw new NoSuchElementException("key is not stored in this collection");

	}

	/**
	 * remove all key-value pairs from this collection
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.buckets = (LProbMapEntry<KeyType, ValueType>[]) new LProbMapEntry[this.capacity];
		this.size = 0;
	}

	/**
	 * retrieve the number of keys stored within this collection
	 */
	@Override
	public int getSize() {
		return this.size;
	}

	/**
	 * Retrieve this collection's capacity (size of its underlying array)
	 */
	@Override
	public int getCapacity() {
		return this.capacity;

	}
}
