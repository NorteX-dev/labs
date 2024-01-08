var a = 0;
var b = 1;
var c = 0;
for (var i = 0; i < 5; i++) {
	c = a + b;
	a = b;
	b = c;
}
console.log(c);
