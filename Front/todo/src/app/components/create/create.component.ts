import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Todo } from "../models/todo";
import { TodoService } from "./../../services/todo.service";

@Component({
  selector: "app-create",
  templateUrl: "./create.component.html",
  styleUrls: ["./create.component.css"],
})
export class CreateComponent implements OnInit {
  constructor(private router: Router, private todoService: TodoService) {}

  todo: Todo = {
    titulo: "",
    descricao: "",
    dataFinalizar: new Date(),
    finalizado: false,
  };
  ngOnInit(): void {}

  create(): void {
    this.formataData();
    this.todoService.create(this.todo).subscribe(
      (resposta) => {
        this.todoService.message("To-Do Criado com Sucesso!!!");
        this.router.navigate([""]);
      },
      (error) => {
        this.todoService.message("Falha ao criar To-Do");
        this.router.navigate([""]);
      }
    );
  }

  cancel(): void {
    this.router.navigate([""]);
  }

  formataData(): void {
    let data = new Date(this.todo.dataFinalizar);
    this.todo.dataFinalizar = `${data.getDate()}/${
      data.getMonth() + 1
    }/${data.getFullYear()}`;
  }
}
