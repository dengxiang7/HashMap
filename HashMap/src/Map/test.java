package Map;

public class test {
	
	public static void main(String[] args) {
		
		Long l1=System.currentTimeMillis();
		HashMap< String, String> myHashMap=new HashMap<String, String>();
		for(int i=0;i<1000;i++)
		{
			myHashMap.put(i+"", i+"");
		}
		for(int i=0;i<1000;i++)
		{
			System.out.println("Key:"+i+"   value:"+myHashMap.get(i+""));
		}
		Long l2=System.currentTimeMillis();
		
		System.out.println("我的HashMap用时为："+(l2-l1));
		System.out.println("-----------------------------------------------------");
		
		Long l3=System.currentTimeMillis();
		java.util.HashMap jdkHash=new java.util.HashMap();
		for(int i=0;i<1000;i++)
		{
			jdkHash.put(i+"", i+"");
		}
		for(int i=0;i<1000;i++)
		{
			System.out.println("Key:"+i+"   value:"+jdkHash.get(i+""));
		}
		Long l4=System.currentTimeMillis();
		
		System.out.println("jdkHash用时为："+(l4-l3));
	}

}
