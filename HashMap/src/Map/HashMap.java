package Map;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K,V> implements Map<K,V>{
	
	private static float DEFAULT_LOAD_FACTOR=0.75f;

	private static int defaultLength=16;
	
	private Entry<K,V> []table=null;
	
	private int size=0;
	
	
	public HashMap(int length,float Loader)
	{
		this.defaultLength=length;
		this.DEFAULT_LOAD_FACTOR=Loader;
		
		table=new Entry[defaultLength];
	}
	
	public HashMap()
	{
		this(defaultLength,DEFAULT_LOAD_FACTOR);
	}
	
	
    
	public V put(K key, V value) {
		
		
		//ÅÐ¶ÏÊÇ·ñÀ©ÈÝ
		if(size>=defaultLength * DEFAULT_LOAD_FACTOR)
		{
			 newCapacity();
		}
		
		int index=getIndex(key);
		Entry<K,V> entry=table[index];
		
		
		if(entry ==null)
		{
			table[index]=newEntry(key, value, null);
			size++;
		}else{
			table[index]=newEntry(key, value, entry);
		}
		
		
		return table[index].getValue();
	}

	
	
	private void newCapacity()
	{
		Entry <K,V> []newTable=new Entry[2* defaultLength];
		
		//ÔÚÉ¢ÁÐ
		againHash(newTable);
	}
	
	
	private void againHash(Entry <K,V> []newTable)
	{
		
		List<Entry<K,V>> list=new ArrayList<Entry<K,V>>();
		
		for(int i=0;i<table.length;i++)
		{
			if(table[i]==null)
			{
				continue;
			}
			else{
				findEntry(table[i],list);
			}
		}
		
		if(list.size()>0)
		{
			size=0;
			defaultLength=defaultLength * 2;
			table=newTable;
			
			for(Entry<K,V> entry : list)
			{
				if(entry.next != null)
				{
					entry.next=null;
				}
				put(entry.getKey(),entry.getValue());
			}
			
		}
		
	}
	
	
	
	private void findEntry(Entry <K,V> entry,List<Entry<K,V>> list)
	{
		if(entry !=null && entry.next !=null)
		{
			list.add(entry);
			findEntry(entry.next,list);
		}else{
			list.add(entry);
		}
	}
	
	
	private Entry<K,V> newEntry(K k,V v,Entry next)
	{
		return new Entry<K,V>(k, v, next);
	}
	
	
	
	public V get(K key) {
		
		int index=getIndex(key);
		if(table[index] ==null)
		{
			return null;
		}
		
		return getValueByKey(key,table[index]);
	}
	
	private V getValueByKey(K k,Entry<K,V> entry)
	{
		if(k==entry.getKey() || k.equals(entry.getKey()))
		{
			return entry.getValue();
		}
		else{
			if(entry.next !=null)
			{
			 return getValueByKey(k,entry.next);
			}
		}
		
		return entry.getValue();
	}
	
	private int getIndex(K k){
		int m=defaultLength;
		
		int index=k.hashCode()%m;
		
		
		return index >=0 ? index : -index;
	}
	
	public int size() {
		return size;
	}
	


	
	public class Entry<K,V> implements Map.Entry<K, V>{

		
		K k;
		
		V v;
		
		Entry<K,V> next;
		
		public Entry(K key,V value,Entry next)
		{
			this.k=key;
			this.v=value;
			this.next=next;
		}
		
		
		public K getKey() {
			return k;
		}

		public V getValue() {
			return v;
		}
		
	}




	





}
