public class NBody{
	public static double readRadius(String file){
		In in_file = new In(file);
		in_file.readInt();
		return in_file.readDouble();
	}
	public static Planet[] readPlanets(String file){
		In in_file = new In(file);
		int N = in_file.readInt();
		in_file.readDouble();
		int i;
		Planet[] all_stars = new Planet[N];
		for (i = 0; i < N; i++){
			double xp = in_file.readDouble();
			double yp = in_file.readDouble();
			double xv = in_file.readDouble();
			double yv = in_file.readDouble();
			double m = in_file.readDouble();
			String img = in_file.readString();
			all_stars[i] = new Planet(xp, yp, xv, yv, m, img);
		}
		return all_stars;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] stars = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		// picture centered around cooridnate(radius, radius)
		StdDraw.picture(0, 0, "./images/starfield.jpg");

		for (Planet p : stars){
			p.draw();
		}
	}
}