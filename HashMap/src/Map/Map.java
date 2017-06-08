package Map;

public interface Map<K,V> {

	public V put(K key,V value);
	
	public V get(K key);
	
	public int size();
	
	
	public interface Entry<K ,V >{
		
		public K getKey();
		
		public V getValue();
		
	}
	
}
