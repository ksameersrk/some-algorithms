package me.sameerkulkarni.projects.dsad.assgin2.one;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class One {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, 5, 7));
        tasks.add(new Task(2, 1, 2));
        tasks.add(new Task(3, 8, 2));
        tasks.add(new Task(4, 5, 4));
        tasks.add(new Task(5, 3, 7));
        tasks.add(new Task(6, 4, 4));
        schedule(tasks);
    }

    private static void schedule(List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(Task::getPmi));
        int previousPmiEndTime = 0;
        for (Task task : tasks) {
            task.setPmiStartTime(previousPmiEndTime);
            previousPmiEndTime += task.getPmi();
            task.setAiStartTime(previousPmiEndTime);
        }
        int previousAiEndTime = 0;
        for (Task task : tasks) {
            if (previousAiEndTime == 0) {
                previousAiEndTime = task.getAiStartTime() + task.getAi();
            } else {
                if (previousAiEndTime > task.getAiStartTime()) {
                    task.setAiStartTime(previousAiEndTime);
                    previousAiEndTime = task.getAi() + previousAiEndTime;
                } else {
                    previousAiEndTime = task.getAi() + task.getAiStartTime();
                }
            }
        }
        System.out.println("Optimal Schedule is as follows :");
        tasks.forEach(System.out::println);
        System.out.println();
        tasks.sort(Comparator.comparingInt(Task::getPmiStartTime));
        System.out.println("Order for Parts Manufacturing : ");
        tasks.forEach(task -> System.out.println(task.getPmiString()));
        System.out.println();
        System.out.println("Order for Assembling : ");
        tasks.sort(Comparator.comparingInt(Task::getAiStartTime));
        tasks.forEach(task -> System.out.println(task.getAiString()));
        System.out.println();
        if (tasks.size() > 1) {
            Task lastTask = tasks.get(tasks.size() - 1);
            System.out.println("Total time for production is : " + (lastTask.getAiStartTime() + lastTask.getAi()));
        }
    }

    static class Task {
        int mobildId;
        int pmi;
        int ai;
        int pmiStartTime;
        int aiStartTime;

        public Task(int mobildId, int pmi, int ai) {
            this.mobildId = mobildId;
            this.pmi = pmi;
            this.ai = ai;
            this.pmiStartTime = -1;
            this.aiStartTime = -1;
        }

        public int getMobildId() {
            return mobildId;
        }

        public int getPmi() {
            return pmi;
        }

        public int getAi() {
            return ai;
        }

        public int getPmiStartTime() {
            return pmiStartTime;
        }

        public void setPmiStartTime(int pmiStartTime) {
            this.pmiStartTime = pmiStartTime;
        }

        public int getAiStartTime() {
            return aiStartTime;
        }

        public void setAiStartTime(int aiStartTime) {
            this.aiStartTime = aiStartTime;
        }

        public String getPmiString() {
            return String.format("Task = %d, PMI {start, end} = (%2d:%2d]",
                    mobildId, pmiStartTime, pmiStartTime + pmi);
        }

        public String getAiString() {
            return String.format("Task = %d, AI {start, end} = (%2d:%2d]",
                    mobildId, aiStartTime, aiStartTime + ai);
        }

        @Override
        public String toString() {
            return String.format("Task = %d, PMI {start, end} = (%2d:%2d], AI {start, end} = (%2d:%2d]",
                    mobildId, pmiStartTime, pmiStartTime + pmi, aiStartTime, aiStartTime + ai);
        }
    }
}
