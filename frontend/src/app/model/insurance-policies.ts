export class InsurancePolicies {
    id:string
    type:string
    amount:number
    maxClaim:number
    policyTerm:number
    description:Array<string>
    constructor( id:string,
        type:string,
        amount:number,
        maxClaim:number,
        policyTerm:number,
        description:Array<string>){
            this.id=id;
            this.type=type;
            this.amount=amount;
            this.maxClaim=maxClaim;
            this.policyTerm=policyTerm;
            this.description=description;
    }

}
