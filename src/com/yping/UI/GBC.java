package com.yping.UI;
/**
 *This class simplifies the use of the GridBagConstraints class.
 *@version 1.00 2011-6-5 5:48
 *@authour —Ó∆Ω
 */ 
import java.awt.*;
public class GBC extends GridBagConstraints
{
	/**
	 *Construts a GBC with a given gridx and gridy position and all other grid
	 *bag constraint value set to default.
	 */
	public GBC(int gridx,int gridy)
	{
		this.gridx=gridx;
		this.gridy=gridy;
	}
	public GBC(int gridx,int gridy,int gridwidth,int gridheight)
	{
		this.gridx=gridx;
		this.gridy=gridy;
		this.gridwidth=gridwidth;
		this.gridheight=gridheight;
	} 
	/**
	 *Set the anchor.
	 *@param anchor the anchor value.
	 *@return this object for further modification.
	 */
	 public GBC setAnchor(int anchor)
	 {
	 	 this.anchor=anchor;
	 	 return this;
	 }
/**
*Set the fill direction.
*@param fill the fill direction.
*@return this object for further modification.
*/
 public GBC setFill(int fill)
 {
 	this.fill=fill;
 	return this;
 }
/**
*Set the cell weights.
*@param weightx the cell weight in x-direction
*@param weighty the cell weight in y-direction
*@return this object for further modification.
*/
public GBC setWeight(double weightx,double weighty)
{
	this.weightx=weightx;
	this.weighty=weighty;
	return this;
}
/**
*Set the insets of this cell.
*@param distance the spacing to use all directions.
*@return this object for further modification.
*/
public GBC setInsets(int distance)
{
	this.insets=new Insets(distance,distance,distance,distance);
	return this;
}
/**
*Set the inset of this cell.
*@param top the spacing to use on top.
*@param left the spacing to use on left.
*@param bottom the spacing to use on botton.
*@param right the spacing to use on right.
*@return this.
*/
public GBC setInsets(int top,int left,int bottom,int right)
{
	this.insets=new Insets(top,left,bottom,right);
	return this;
}
/**
*Set the interanl padding.
*@param ipadx the internal padding in x-direction
*@param ipady the internal padding in y-direction
*@return this object for further modification
*/
public GBC setIpad(int ipadx,int ipady)
{
	this.ipadx=ipadx;
	this.ipady=ipady;
	return this;
}

}