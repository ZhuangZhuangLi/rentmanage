package rentmanage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.io.CharToByteConverter;
import sun.io.ByteToCharConverter;

/**
* 创建日期：(2008-7-6 13:51:16) 解决sybase数据库查询不能显示中文的问题
* 
* @author：like_dark
*/
public class DataUtil
{
public static void main(String[] args) {
	System.out.println(AsciiToChineseString("日"));
	System.out.println(ChineseStringToAscii("日"));
	 System.out.println(tran("22")); 
}

private static Pattern pattern = Pattern.compile("\\&\\#(\\d+)");    
 public static String tran(String str){   
    StringBuilder sb = new StringBuilder();   
        Matcher m =pattern.matcher(str);   
          
       while (m.find()){   
            sb.append((char)Integer.valueOf(m.group(1)).intValue());   
       }   
           
       return sb.toString();   
   }   


/**
* 将Ascii转换成中文字符串
*/
public static String AsciiToChineseString ( String s )
{
   if ( s == null )
    return s;
   char[] orig = s.toCharArray ();
   byte[] dest = new byte[ orig.length ];
   for ( int i = 0; i < orig.length; i++ )
    dest[ i ] = ( byte ) ( orig[ i ] & 0xFF );
   try
   {
    ByteToCharConverter toChar = ByteToCharConverter.getConverter ( "gb2312" );
    return new String ( toChar.convertAll ( dest ) );
   }
   catch ( Exception e )
   {
    System.out.println ( e );
    return s;
   }
}

/**
* 将中文字符串转换成Ascii
*/
public static String ChineseStringToAscii ( String s )
{
   if ( s == null )
    return s;
   try
   {
    CharToByteConverter toByte = CharToByteConverter.getConverter ( "gb2312" );
    byte[] orig = toByte.convertAll ( s.toCharArray () );
    char[] dest = new char[ orig.length ];
    for ( int i = 0; i < orig.length; i++ )
     dest[ i ] = ( char ) ( orig[ i ] & 0xFF );
    return new String ( dest );
   }
   catch ( Exception e )
   {
    System.out.println ( e );
    return s;
   }
}

/**
* 中文转ascii
* 
* @param s
*            要进行转换的字符串
* @param bl
*            是否进行转换,一个开关控制 , true代表需要转换。
* @return 转换后的字符串
*/

public static String ChineseStringToAscii ( String s, boolean bl )
{
   if ( !bl )
    return s;
   else
    return ChineseStringToAscii ( s );
}

/**
* ascii转字符串
* 
* @param s
* @param bl
* @return
*/
public static String AsciiToChineseString ( String s, boolean bl )
{
   if ( !bl )
    return s;
   else
    return AsciiToChineseString ( s );
}

/**
* 根据输入的源串(中文或中西文混合)返回其拼音首字母,以小写返回,如果首字符非拼音字母,则统一返回*号
* 
* @param str
*            源串(中文或中西文混合)
* @return 返回str的拼音首字母,以小写返回,如果首字符非拼音字母,则统一返回*号
*/
public static String getFirstCharOfString ( String str )
{
   String firstChar = "*";

   if ( str == null || str.length () <= 0 )
    return firstChar;

   try
   {
    byte firstCharBytes[] = new byte[ 2 ];
    int gbcode;

    firstCharBytes[ 0 ] = str.getBytes ( "gb2312" )[ 0 ];
    gbcode = firstCharBytes[ 0 ] & 0x000000ff;
    if ( str.length () > 1 || gbcode >= 0xb0 )
    {
     firstCharBytes[ 1 ] = str.getBytes ( "gb2312" )[ 1 ];
     gbcode = ( firstCharBytes[ 0 ] & 0x000000ff ) * 0x100 + ( firstCharBytes[ 1 ] & 0x000000ff );
    }

    if ( gbcode >= 0xb0a1 && gbcode <= 0xb0c4 )
     firstChar = "a";
    else if ( gbcode >= 0xb0c5 && gbcode <= 0xb2c0 )
     firstChar = "b";
    else if ( gbcode >= 0xb2c1 && gbcode <= 0xb4ed )
     firstChar = "c";
    else if ( gbcode >= 0xb4ee && gbcode <= 0xb6e9 )
     firstChar = "d";
    else if ( gbcode >= 0xb6ea && gbcode <= 0xb7a1 )
     firstChar = "e";
    else if ( gbcode >= 0xb7a2 && gbcode <= 0xb8c0 )
     firstChar = "f";
    else if ( gbcode >= 0xb8c1 && gbcode <= 0xb9fd )
     firstChar = "g";
    else if ( gbcode >= 0xb9fe && gbcode <= 0xbbf6 )
     firstChar = "h";
    else if ( gbcode >= 0xbbf7 && gbcode <= 0xbfa5 )
     firstChar = "j";
    else if ( gbcode >= 0xbfa6 && gbcode <= 0xc0ab )
     firstChar = "k";
    else if ( gbcode >= 0xc0ac && gbcode <= 0xc2e7 )
     firstChar = "l";
    else if ( gbcode >= 0xc2e8 && gbcode <= 0xc4c2 )
     firstChar = "m";
    else if ( gbcode >= 0xc4c3 && gbcode <= 0xc5b5 )
     firstChar = "n";
    else if ( gbcode >= 0xc5b6 && gbcode <= 0xc5bd )
     firstChar = "o";
    else if ( gbcode >= 0xc5be && gbcode <= 0xc6d9 )
     firstChar = "p";
    else if ( gbcode >= 0xc6da && gbcode <= 0xc8ba )
     firstChar = "q";
    else if ( gbcode >= 0xc8bb && gbcode <= 0xc8f5 )
     firstChar = "r";
    else if ( gbcode >= 0xc8f6 && gbcode <= 0xcbf9 )
     firstChar = "s";
    else if ( gbcode >= 0xcbfa && gbcode <= 0xcdd9 )
     firstChar = "t";
    else if ( gbcode >= 0xcdda && gbcode <= 0xcef3 )
     firstChar = "w";
    else if ( gbcode >= 0xcef4 && gbcode <= 0xd1b8 )
     firstChar = "x";
    else if ( gbcode >= 0xd1b9 && gbcode <= 0xd4d0 )
     firstChar = "y";
    else if ( gbcode >= 0xd4d1 && gbcode <= 0xd7f9 )
     firstChar = "z";
    else
     gbcode = firstCharBytes[ 0 ];

    if ( gbcode >= 'A' && gbcode <= 'Z' )
     gbcode += 32;
    if ( gbcode >= 'a' && gbcode <= 'z' )
     firstChar = String.valueOf ( ( char ) gbcode );
   }
   catch ( Exception e )
   {
    System.out.println ( "getFirstCharOfString Exception: " + e.getMessage () );
   }

   return firstChar;
}
}




