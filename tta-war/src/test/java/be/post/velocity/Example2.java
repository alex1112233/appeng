package be.post.velocity;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;

public class Example2 {

	public static void main( String args[] )
    {
        /* first, we init the runtime engine.  Defaults are fine. */

        try
        {
            Velocity.init();
        }
        catch(Exception e)
        {
            System.out.println("Problem initializing Velocity : " + e );
            return;
        }

        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("project", "Jakarta");

        /* lets render a template */

        StringWriter w = new StringWriter();

        try
        {
            Velocity.mergeTemplate("./src/test/java/example2.vm", "ISO-8859-1", context, w );
        }
        catch (Exception e )
        {
            System.out.println("Problem merging template : " + e );
        }

        System.out.println(" template : " + w );

        /*
         *  lets dynamically 'create' our template
         *  and use the evaluate() method to render it
         */

        String s = "We are using $project $name to render this.";
        w = new StringWriter();

        try
        {
            Velocity.evaluate( context, w, "mystring", s );
        }
        catch( ParseErrorException pee )
        {
            /*
             * thrown if something is wrong with the
             * syntax of our template string
             */
            System.out.println("ParseErrorException : " + pee );
        }
        catch( MethodInvocationException mee )
        {
            /*
             *  thrown if a method of a reference
             *  called by the template
             *  throws an exception. That won't happen here
             *  as we aren't calling any methods in this
             *  example, but we have to catch them anyway
             */
            System.out.println("MethodInvocationException : " + mee );
        }
        catch( Exception e )
        {
            System.out.println("Exception : " + e );
        }

        System.out.println(" string : " + w );
    }
}
