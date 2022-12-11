grammar Number;

parse returns [List<List<Integer>> numbers]
@init {
  $numbers = new ArrayList<List<Integer>>();
}
  :  (line {$numbers.add($line.row);})* EOF
  ;

line returns [List<Integer> row]
@init {
  $row = new ArrayList<Integer>();
}
  :  (Number {$row.add(Integer.parseInt($Number.text));})+ (LineBreak | EOF)
  ;

Number
  :  ('0'..'9')+
  ;

Space
  :  (' ' | '\t') {skip();}
  ;

LineBreak
  :  '\r'? '\n'
  |  '\r'
  ;