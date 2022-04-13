export class Point2D {

    private pointX: number;
    private pointY: number;

    constructor(pointX : number, pointY : number) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public move(newPointX : number, newPointY : number) {
        this.pointX += newPointX;
        this.pointY += newPointY;
    }

    public getPointX() : number {
        return this.pointX;
    }

    public getPointY() : number {
        return this.pointY;
    }
}

export class Rectangle {
    private point0 : Point2D;
    private point1 : Point2D;
    private point2 : Point2D;
    private point3 : Point2D;

    constructor(point0 : Point2D, point1 : Point2D, point2 : Point2D, point3 : Point2D) {
        this.point0 = point0;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public move(newPositionValueX : number, newPositionValueY : number) {
        this.point0.move(newPositionValueX, newPositionValueY);
        this.point1.move(newPositionValueX, newPositionValueY);
        this.point2.move(newPositionValueX, newPositionValueY);
        this.point3.move(newPositionValueX, newPositionValueY);
    }

    public getArea() {
        let distanceA = Math.sqrt(Math.pow(this.point1.getPointX() - this.point0.getPointX(), 2) + Math.pow(this.point1.getPointY() - this.point0.getPointY(), 2))
        let distanceB = Math.sqrt(Math.pow(this.point3.getPointX() - this.point1.getPointX(), 2) + Math.pow(this.point3.getPointY() - this.point1.getPointY(), 2))

        return distanceA * distanceB
    }
}

let firstPoint = new Point2D(0, 0)
let secPoint = new Point2D(0, 4)
let thirdPoint = new Point2D(4, 0)
let fourthPoint = new Point2D(4, 4)

let rectangle = new Rectangle(firstPoint, secPoint, thirdPoint, fourthPoint)

rectangle.move(1, 1)
console.log('first point: ' + ""+firstPoint.getPointX(), ""+firstPoint.getPointY())
console.log('second point: ' + ""+secPoint.getPointX(), ""+secPoint.getPointY())
console.log('third point: ' + ""+thirdPoint.getPointX(), ""+thirdPoint.getPointY())
console.log('fourth point: ' + ""+fourthPoint.getPointX(), ""+fourthPoint.getPointY())
console.log(""+rectangle.getArea())
