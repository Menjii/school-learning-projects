"use strict";
exports.__esModule = true;
exports.Rectangle = exports.Point2D = void 0;
var Point2D = /** @class */ (function () {
    function Point2D(pointX, pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }
    Point2D.prototype.move = function (newPointX, newPointY) {
        this.pointX += newPointX;
        this.pointY += newPointY;
    };
    Point2D.prototype.getPointX = function () {
        return this.pointX;
    };
    Point2D.prototype.getPointY = function () {
        return this.pointY;
    };
    return Point2D;
}());
exports.Point2D = Point2D;
var Rectangle = /** @class */ (function () {
    function Rectangle(point0, point1, point2, point3) {
        this.point0 = point0;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }
    Rectangle.prototype.move = function (newPositionValueX, newPositionValueY) {
        this.point0.move(newPositionValueX, newPositionValueY);
        this.point1.move(newPositionValueX, newPositionValueY);
        this.point2.move(newPositionValueX, newPositionValueY);
        this.point3.move(newPositionValueX, newPositionValueY);
    };
    Rectangle.prototype.getArea = function () {
        var distanceA = Math.sqrt(Math.pow(this.point1.getPointX() - this.point0.getPointX(), 2) + Math.pow(this.point1.getPointY() - this.point0.getPointY(), 2));
        var distanceB = Math.sqrt(Math.pow(this.point3.getPointX() - this.point1.getPointX(), 2) + Math.pow(this.point3.getPointY() - this.point1.getPointY(), 2));
        return distanceA * distanceB;
    };
    return Rectangle;
}());
exports.Rectangle = Rectangle;
var firstPoint = new Point2D(0, 0);
var secPoint = new Point2D(0, 4);
var thirdPoint = new Point2D(4, 0);
var fourthPoint = new Point2D(4, 4);
var rectangle = new Rectangle(firstPoint, secPoint, thirdPoint, fourthPoint);
rectangle.move(1, 1);
console.log('first point: ' + "" + firstPoint.getPointX(), "" + firstPoint.getPointY());
console.log('second point: ' + "" + secPoint.getPointX(), "" + secPoint.getPointY());
console.log('third point: ' + "" + thirdPoint.getPointX(), "" + thirdPoint.getPointY());
console.log('fourth point: ' + "" + fourthPoint.getPointX(), "" + fourthPoint.getPointY());
console.log("" + rectangle.getArea());
