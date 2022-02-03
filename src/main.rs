use std::io;
use std::io::prelude::*;
use std::collections::VecDeque;

fn main() {

    let input = io::stdin();

 
    let mut number_of_cmds: u16 = 0;

    let mut is_stack = true;
    let mut is_priority = true;
    let mut is_queue = true;

    let mut stack:Vec<u16> = vec![];
    let mut priority:Vec<u16> = vec![];
    let mut queue: VecDeque<u16> = VecDeque::with_capacity(1);


    for _line in input.lock().lines().map(|_line| _line.unwrap()) {

        if !_line.contains(" "){

            number_of_cmds = _line.parse::<u16>().unwrap();

            queue = VecDeque::with_capacity(number_of_cmds as usize);
            stack = vec![];
            priority = vec![];

        }else {

            let commands:Vec<u16> = _line.split_whitespace()
            .map(|_c| _c.trim())
            .filter(|_c| !_c.is_empty())        // Kanske ta bort
            .map(|_c| _c.parse::<u16>().unwrap())
            .collect();

            if commands[0] == 1 {
                stack.push(commands[1]);
                priority.push(commands[1]);
                queue.push_back(commands[1]);
                number_of_cmds = number_of_cmds - 1;
            }else{
                if is_stack || is_queue || is_priority {
                    if !priority.contains(&commands[1]) && !queue.contains(&commands[1]) && !stack.contains(&commands[1]) {
                        is_stack = false;
                        is_queue = false;
                        is_priority = false;
                    }else{
                        if is_stack {
                            if stack.pop() != Some(commands[1]){
                                is_stack = false;
                            }
                        }
                        let max_pri = priority.iter().max().unwrap();
                        if is_priority {
                            if *max_pri != commands[1] {
                                is_priority = false;
                            }
                            if priority.contains(max_pri){
                                let index = priority.iter().position(|x| *x == *max_pri).unwrap();
                                priority.remove(index);
                            }
                        }
                        if is_queue {
                            if queue.pop_front() != Some(commands[1]){
                                is_queue = false;
                            }
                        }
                    } 
                } 
                number_of_cmds = number_of_cmds - 1;
            }

            if number_of_cmds == 0 {
                if is_stack && !is_queue && !is_priority {
                    println!("stack");
                }else if !is_stack && is_queue && !is_priority {
                    println!("queue");
                }else if !is_stack && !is_queue && is_priority {
                    println!("priority queue");
                }else if !is_stack && !is_queue && !is_priority {
                    println!("impossible");
                }else{
                    println!("not sure");
                }

                is_stack = true;
                is_priority = true;
                is_queue = true;

            }

        }
    }


}
