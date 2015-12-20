
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class FrontBookkeeper61833 implements IFrontBookkeeper {
	private HashMap<String, LinkedList<Integer>> units = new HashMap<>();
	private HashMap<String, String> attachments = new HashMap<>();
	private HashMap<Integer, LinkedList<String>> soldiers = new HashMap<>();
	private StringBuilder result = new StringBuilder();

	private static int getNumber(String task) {
		int result = 0;
		for (int i = 0; i < task.length(); i++) {
			if (Character.isDigit(task.charAt(i))) {
				result *= 10;
				result += Integer.parseInt(String.valueOf(task.charAt(i)));
			}
		}
		return result;
	}

	public String updateFront(String[] news) {
		for (String task : news) {
			String[] splittedTask = task.split("\\s+");
			int kindOfTask = 0;
			for (String word : splittedTask) {
				if (word.equals("=")) {
					kindOfTask = 1;
					break;
				} else if (word.equals("attached")) {
					if (splittedTask.length == 4) {
						kindOfTask = 2;
						break;
					} else if (splittedTask.length == 7) {
						kindOfTask = 3;
						break;
					}
				} else if (word.equals("died")) {
					kindOfTask = 4;
					break;
				} else if (word.equals("show")) {
					if (!splittedTask[1].equals("soldier")) {
						kindOfTask = 5;
						break;
					} else {
						kindOfTask = 6;
						break;
					}
				} else {
					// TODO:throw exception
				}
			}
			doTask(kindOfTask, task);
		}
		return result.toString();
	}

	private void doTask(int kindOfTask, String task) {
		String[] splittedTask = task.split(" ");
		if (kindOfTask == 1) {
			for (String currTask : splittedTask) {
				if (currTask.contains(",") || currTask.contains("]")) {
					LinkedList<Integer> newUnit;
					if (units.get(splittedTask[0]) != null) {
						newUnit = new LinkedList<Integer>(units.get(splittedTask[0]));
					} else {
						newUnit = new LinkedList<>();
					}
					if (!currTask.equals("[]")) {
						int number = getNumber(currTask);
						if (soldiers.get(number) == null) {
							soldiers.put(number, new LinkedList<String>());
						}
						soldiers.get(number).add(splittedTask[0]);
						newUnit.add(number);
					}
					units.put(splittedTask[0], newUnit);
				}
			}
		}

		else if (kindOfTask == 2) {
			String unit1 = splittedTask[0];
			String unit2 = splittedTask[splittedTask.length - 1];
			if (attachments.get(unit1) != null) {
				LinkedList<Integer> list = new LinkedList<>(units.get(unit1));
				for (Integer item : list) {
					removeItem(item, attachments.get(unit1));
				}
			}
			attachments.put(unit1, unit2);
			units.get(unit2).addAll(units.get(unit1));
			for (Integer soldier : units.get(unit1)) {
				soldiers.get(soldier).add(unit2);
			}
		}

		else if (kindOfTask == 3) {
			String unit1 = splittedTask[0];
			String unit2 = splittedTask[3];
			int index = units.get(unit2).indexOf(Integer.parseInt(splittedTask[splittedTask.length - 1]));
			if (attachments.get(unit1) != null) {
				LinkedList<Integer> list = new LinkedList<>(units.get(unit1));
				for (Integer item : list) {
					removeItem(item, attachments.get(unit1));
				}
			}
			attachments.put(unit1, unit2);
			units.get(unit2).addAll(index + 1, units.get(unit1));
			for (Integer soldier : units.get(unit1)) {
				soldiers.get(soldier).add(unit2);
			}
		}

		else if (kindOfTask == 4) {
			String unit1 = splittedTask[6];
			int soldier1 = Integer.parseInt(splittedTask[2]);
			int soldier2 = Integer.parseInt(splittedTask[4]);
			if (units.get(unit1).contains(soldier1) && units.get(unit1).contains(soldier2)) {
				int start = units.get(unit1).indexOf(soldier1);
				int end = units.get(unit1).indexOf(soldier2);
				LinkedList<Integer> list = new LinkedList(units.get(unit1));
				for (int i = start; i <= end; i++) {
					int currSoldier = list.get(i);
					removeItem(currSoldier, unit1);
					removeFromAttached(currSoldier, unit1);
					soldiers.get(currSoldier).remove(unit1);
				}
			}
		}

		else if (kindOfTask == 5) {
			if (result.length() != 0) {
				result.append(System.lineSeparator());
			}
			result.append(units.get(splittedTask[1]).toString());
		}

		else if (kindOfTask == 6) {
			if (result.length() != 0) {
				result.append(System.lineSeparator());
			}
			
			for (String unit : soldiers.get(Integer.parseInt(splittedTask[2]))) {
				result.append(unit + ", ");
			}
			result.replace(result.length() - 2, result.length(), "");
		}
	}

	private void removeFromAttached(int soldier, String unit1) {
		for (String unit : soldiers.get(soldier)) {
			removeItem(soldier, unit);
		}
	}

	private void removeItem(int soldier, String unit1) {
		while (units.get(unit1) != null && units.get(unit1).contains(soldier)) {
			int index = units.get(unit1).indexOf(soldier);
			soldiers.get(soldier).remove(unit1);
			units.get(unit1).subList(index, index + 1).clear();
			if (attachments.get(unit1) != null) {
				removeItem(soldier, attachments.get(unit1));
			}
		}
	}
}
