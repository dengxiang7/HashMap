package Map;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K,V> implements Map<K,V>{
	
	private static float DEFAULT_LOAD_FACTOR=0.75f; //负载因子

	private static int defaultLength=16;
	
	private Entry<K,V> []table=null;  //数据的 数组
	
	private int size=0;    //在数组中      已存数据的  数量   不包括链表中的数据。
	
	
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
		
		
		//判断是否扩容  依据       数组已存数据大小size  大于     数组原小 * 负载因子
		if(size>=defaultLength * DEFAULT_LOAD_FACTOR)
		{
			 newCapacity();
		}
		
		//通过散列函数   获取存储位置
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

	
	//扩容  大小为2倍
	private void newCapacity()
	{
		Entry <K,V> []newTable=new Entry[2* defaultLength];
		
		//元数据散列 再存在新容器
		againHash(newTable);
	}
	
	
	private void againHash(Entry <K,V> []newTable)
	{
		//取出数据
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
		
		//存储
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
	
	
	//取出原数据
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
	
	//通过key取出数据    如果存在冲突   则 递归 查找 该链表
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
	
	//获取数组的下标， hash函数  使用的是保留取余法。
	private int getIndex(K k){
		int m=defaultLength;
		
		int index=k.hashCode()%m;
		
		
		return index >=0 ? index : -index;
	}
	
	public int size() {
		return size;
	}
	


	//内部存储类
	public class Entry<K,V> implements Map.Entry<K, V>{

		
		K k;
		
		V v;
		
		//解决冲突使用的链表
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
