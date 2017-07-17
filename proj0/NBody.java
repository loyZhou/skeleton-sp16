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
		// picture centered around cooridnate(0, 0)
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		// draw all planets
		for (Planet p : stars){
			p.draw();
		}

		//play Audio
		StdAudio.play("./audio/2001.mid");

		double time = 0.0;
		int n = stars.length;
		while (time <= T){
			double[] xForce = new double[n];
			double[] yForce = new double[n];
			int i;
			for (i = 0; i < n; i++){
				xForce[i] = stars[i].calcNetForceExertedByX(stars);
				yForce[i] = stars[i].calcNetForceExertedByY(stars);
			}
			for (i = 0; i < n; i++){
				stars[i].update(dt, xForce[i], yForce[i]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for (Planet p : stars){
				p.draw();
			}
			StdDraw.show(10);
			time += dt;
		}
		StdOut.printf("%d\n", n);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < n; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		stars[i].xxPos, stars[i].yyPos, stars[i].xxVel, stars[i].yyVel, stars[i].mass, stars[i].imgFileName);	
		}
	}

}