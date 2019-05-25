export function getExpect(list, year, modulus) {
  if (modulus <= 0 || modulus >= 1) {
    return null;
  }
  var length = list.length;
  var modulusLeft = 1- modulus;
  var lastIndex = list[0];
  var lastSeclndex = list[0];
  for (var i = 0; i < length; i++) {
    lastIndex = modulus * list[i] + modulusLeft * lastIndex;
    lastSeclndex = modulus * lastIndex + modulusLeft * lastSeclndex;
  }
  var a = 2 * lastIndex - lastSeclndex;
  var b = (modulus / modulusLeft) * (lastIndex - lastSeclndex);
  return a + b * year;
}
