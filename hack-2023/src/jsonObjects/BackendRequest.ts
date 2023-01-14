export class BackendRequest {
   private type: string;
   private data: string;

    constructor(type: string, data: string) {
        this.type = type;
        this.data = data;
    }
}