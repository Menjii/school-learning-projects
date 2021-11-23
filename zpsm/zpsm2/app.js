const { toInteger, map } = require('lodash');
const _ = require('lodash');

const user = {
  name: 'Imie',
  surname: 'Nazwisko',
  allGrades: [
    {
      subjectName: 'Name1',
      grades: [5, 4, 3, 5, 2],
      weight: 3,
    },
    {
      subjectName: 'Name2',
      grades: [3, 3.5, 2],
      weight: 1,
    },
    {
      subjectName: 'Name3',
      grades: [4, 3, 3.5],
      weight: 5,
    },
  ],
};

const collections = [
  {},
  15,
  'hello@test.pl',
  null,
  ['aaa', 'bbb', 5],
  'admin@gmail.com',
  undefined,
  'a34@yahoo.com',
  '321@a',
  '321.pl',
];

const userInformation = function (item) {
  let mean = 0;
  console.log('Dane: ' + item.name + ' ' + item.surname);
  for (let i = 0; i < item.allGrades.length; i++) {
    for (let j = 0; j < item.allGrades[i].grades.length; j++) {
      mean += item.allGrades[i].grades[j] * item.allGrades[i].weight;
    }
    console.log((mean /= item.allGrades[i].grades.length));
    mean = 0;
  }
};

userInformation(user);

let weight = _.find(user.allGrades, function (obj) {
  if (obj.weight == 1) {
    return true;
  }
});

console.log(weight);

const reg =
  /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

const getMails = function (item) {
  let emails = item.filter(function (x) {
    if (reg.test(x) == true) {
      return x;
    }
  });
  return emails.sort();
};

console.log(getMails(collections));

//console.log(reg.test("321.pl"))

/*
let meanValue = _.mean([5, 6, 7, 8, 9, 10]);
console.log(meanValue);

let maxValue = _.max([5, 6, 7, 10, 3]);
console.log(maxValue);

let minValue = _.min([5, 4, 2, 19]);
console.log(minValue);
*/
