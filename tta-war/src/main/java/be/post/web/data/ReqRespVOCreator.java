package be.post.web.data;

public class ReqRespVOCreator {

	public static String create(String className, String jsonHar12Str){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("public class " + className + "{\n" );
		
		
		sb.append("\n}" );
		return sb.toString();
	}
}
