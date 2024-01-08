const a = 2139847;

let b = a;
let outputBin = "";
do {
	outputBin = (b % 2) + outputBin;
	b = Math.floor(b / 2);
} while (b > 0);

console.log(outputBin);
