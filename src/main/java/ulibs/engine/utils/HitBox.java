package main.java.ulibs.engine.utils;

import main.java.ulibs.common.math.Vec2f;
import main.java.ulibs.common.math.Vec2i;
import main.java.ulibs.common.math.Vec4f;
import main.java.ulibs.common.utils.ICopyable;

/** Simple class for checking if areas intersect. <br> Note that W/H are NOT coordinates but instead just a width & height.
 * @author -Unknown-
 */
public class HitBox implements ICopyable<HitBox> {
	/** Coordinate values used for math */
	@SuppressWarnings("javadoc") //Bugs Eclipse out? shows  Y/W/H as undocumented?
	protected final float x, y, w, h;
	
	/** Creates a new HitBox with the given values
	 * @param vec Vec4f to grab coordinates from
	 */
	public HitBox(Vec4f vec) {
		this(vec.getX(), vec.getY(), vec.getZ(), vec.getW());
	}
	
	/** Creates a new HitBox with the given values
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param w Width
	 * @param h Height
	 */
	public HitBox(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	/**@param box The HitBox to check for intersection
	 * @return True if the given HitBox intersects with self
	 */
	public boolean intersects(HitBox box) {
		return intersectMath(box.x, box.y, box.w, box.h);
	}
	
	/**@param vec The Vec2i to check for intersection
	 * @return True if the given position intersects with self
	 */
	public boolean intersectsPoint(Vec2i vec) {
		return intersectsPoint(vec.getX(), vec.getY());
	}
	
	/**@param x The X coordinate to check for intersection
	 * @param y The Y coordinate to check for intersection
	 * @return True if the given position intersects with self
	 */
	public boolean intersectsPoint(int x, int y) {
		return intersectMath(x, y, 1, 1);
	}
	
	private boolean intersectMath(float x, float y, float w, float h) {
		float tw = this.w, th = this.h, rw = w, rh = h;
		
		if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
			return false;
		}
		
		float tx = this.x, ty = this.y;
		
		rw += x;
		rh += y;
		tw += tx;
		th += ty;
		
		return ((rw < x || rw > tx) && (rh < y || rh > ty) && (tw < tx || tw > x) && (th < ty || th > y));
	}
	
	/**@param vec new X/Y coordinates to add to the pre-existing X/Y
	 * @return A new HitBox with the given values
	 */
	public HitBox addXY(Vec2f vec) {
		return new HitBox(x + vec.getX(), y + vec.getY(), w, h);
	}
	
	/**@param x new X coordinate to add to the pre-existing X
	 * @param y new Y coordinate to add to the pre-existing Y
	 * @return A new HitBox with the given values
	 */
	public HitBox addXY(float x, float y) {
		return new HitBox(this.x + x, this.y + y, w, h);
	}
	
	/**@param x new X coordinate to add to the pre-existing X
	 * @return A new HitBox with the given values
	 */
	public HitBox addX(float x) {
		return new HitBox(this.x + x, y, w, h);
	}
	
	/**@param y new Y coordinate to add to the pre-existing Y
	 * @return A new HitBox with the given values
	 */
	public HitBox addY(float y) {
		return new HitBox(x, this.y + y, w, h);
	}
	
	/**@param vec new width/height to add to the pre-existing width/height
	 * @return A new HitBox with the given values
	 */
	public HitBox addWH(Vec2f vec) {
		return new HitBox(x, y, w + vec.getX(), h + vec.getY());
	}
	
	/**@param w new width to add to the pre-existing width
	 * @param h new height to add to the pre-existing height
	 * @return A new HitBox with the given values
	 */
	public HitBox addWH(float w, float h) {
		return new HitBox(x, y, this.w + w, this.h + h);
	}
	
	/**@param w new width to add to the pre-existing width
	 * @return A new HitBox with the given values
	 */
	public HitBox addW(float w) {
		return new HitBox(x, y, this.w + w, h);
	}
	
	/**@param h new height to add to the pre-existing height
	 * @return A new HitBox with the given values
	 */
	public HitBox addH(float h) {
		return new HitBox(x, y, w, this.h + h);
	}
	
	@Override
	public HitBox copy() {
		return new HitBox(x, y, w, h);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + w + ", " + h + ")";
	}
}
