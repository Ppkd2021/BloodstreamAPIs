package utilities;
import java.io.FileInputStream;
import java.util.Properties;


public final class config 
{
	public static Properties property=new Properties();
	//public static Logger log =LogManager.getLogger(config.class.getName());
	public static FileInputStream inputStream = null;
	public static ExcelReader excel = null;
	
	public static void init()
	{
		//log.debug(new String(new char[100]).replace("\0", "-"));	
		//log.debug("Suite invoked");
		if(inputStream==null)
		{
			try
			{
				inputStream=new FileInputStream(System.getProperty("user.dir")+"//global.properties");
				property.load(inputStream);
				inputStream.close();
			}
			catch (Exception ex)
			{
				//log.error(ex);
			}
		}
		//log.debug("Directory: "+System.getProperty("user.dir"));
		//log.debug("Configuration exited");
		//log.debug(new String(new char[100]).replace("\0", "-"));
		
	}

}
