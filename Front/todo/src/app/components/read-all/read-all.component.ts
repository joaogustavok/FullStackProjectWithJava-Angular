import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { TodoService } from "./../../services/todo.service";
import { Todo } from "./../models/todo";

@Component({
  selector: "app-read-all",
  templateUrl: "./read-all.component.html",
  styleUrls: ["./read-all.component.css"],
})
export class ReadAllComponent implements OnInit {
  list: Todo[] = [];
  listFinished: Todo[] = [];
  closed = 0;
  constructor(private todoService: TodoService, private router: Router) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.todoService.findAll().subscribe((resposta) => {
      resposta.forEach((todo) => {
        if (todo.finalizado) {
          this.listFinished.push(todo);
        } else {
          this.list.push(todo);
        }
      });
      this.closed = this.listFinished.length;
    });
  }

  finalizar(item: Todo): void {
    item.finalizado = true;
    this.todoService.uptade(item).subscribe(() => {
      this.todoService.message("Task Finalizada com sucesso !!");
      this.list = this.list.filter((todo) => todo.id !== item.id);
      this.closed++;
    });
  }

  delete(id: any): void {
    this.todoService.delete(id).subscribe((resposta) => {
      if (resposta === null) {
        this.todoService.message("Task Deletada com sucesso !!");
        this.list = this.list.filter((todo) => todo.id !== id);
      }
    });
  }

  navegarParaFinalizados(): void {
    this.router.navigate(["finalizados"]);
  }
}
