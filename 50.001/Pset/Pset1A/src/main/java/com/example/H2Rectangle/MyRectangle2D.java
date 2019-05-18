package com.example.H2Rectangle;

public class MyRectangle2D {
    private double x ;
    private double y ;

    private double width;
    private double height;

    public MyRectangle2D(double x, double y, double width, double height ){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;

    }

    public MyRectangle2D(){
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public double getArea(){
        return width * height;
    }

    public double getPerimeter(){
        return  2*width + 2*height;
    }

    public boolean contains(double x, double y){
        double rightboundx = this.x + 0.5*width;
        double leftboundx = this.x - 0.5*width;
        double topy = this.y + 0.5*height;
        double boty = this.y - 0.5*height;
        if (x <= rightboundx && x >= leftboundx){
            if (y <= topy && y >= boty){
                return true;
            }
        }
        return false;
    }

    public boolean contains(MyRectangle2D r){
        double xmax = r.getX() + 0.5*r.getWidth();
        double xmin = r.getX() - 0.5*r.getWidth();
        double ymax = r.getY() + 0.5*r.getHeight();
        double ymin = r.getY() - 0.5*r.getHeight();
        if (contains(xmax,ymin) && contains(xmax,ymax) && contains(xmin,ymin) && contains(xmin,ymax)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean overlaps(MyRectangle2D r){
        double xmax = r.getX() + 0.5*r.getWidth();
        double xmin = r.getX() - 0.5*r.getWidth();
        double ymax = r.getY() + 0.5*r.getHeight();
        double ymin = r.getY() - 0.5*r.getHeight();
        if (contains(xmax,ymin) || contains(xmax,ymax) || contains(xmin,ymin) || contains(xmin,ymax)){
            return true;
        }

        return false;
    }

}
