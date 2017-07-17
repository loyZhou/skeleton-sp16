import java.lang.Math.*;


public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;


	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;			
	}


	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}


	public double calcDistance(Planet opponent_star){
		//return sqrt(2);
		return Math.sqrt(Math.pow(opponent_star.xxPos - xxPos,2)+Math.pow(opponent_star.yyPos - yyPos,2));
	}


	public double calcForceExertedBy(Planet opponent_star){
		double distance = this.calcDistance(opponent_star);
		return 6.67 * Math.pow(10,-11) * opponent_star.mass * this.mass / Math.pow(distance,2);
	}


	public double calcForceExertedByX(Planet opponent_star){
		double total_force = this.calcForceExertedBy(opponent_star);
		double distance = this.calcDistance(opponent_star);
		return total_force * (opponent_star.xxPos - xxPos) / distance;
	}


	public double calcForceExertedByY(Planet opponent_star){
		double total_force = this.calcForceExertedBy(opponent_star);
		double distance = this.calcDistance(opponent_star);
		return total_force * (opponent_star.yyPos - yyPos) / distance;
	}


	public double calcNetForceExertedByX(Planet[] all_stars){
		double net_force = 0.0;
		int i;
		for (i = 0; i < all_stars.length; i++){
			if (all_stars[i] == this){
				continue;
			}
			else{
				net_force += this.calcForceExertedByX(all_stars[i]);
			}
		}
		return net_force;
	}


	public double calcNetForceExertedByY(Planet[] all_stars){
		double net_force = 0.0;
		int i;
		for (i = 0; i < all_stars.length; i++){
			if (all_stars[i] == this){
				continue;
			}
			else{
				net_force += this.calcForceExertedByY(all_stars[i]);
			}
		}
		return net_force;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}


	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}