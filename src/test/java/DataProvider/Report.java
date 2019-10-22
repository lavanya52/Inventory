/**
 * 
 */
package DataProvider;

/**
 * @author Nive
 *
 */
public interface Report {
	public void setField(int index, String value);
	public String getField(int index);
	public int getSize();
	public String getSeparator();

}
