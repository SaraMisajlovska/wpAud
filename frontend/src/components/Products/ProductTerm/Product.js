import React from "react";


const product = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.price}</td>
            <td scope={"col"}>{props.term.quantity}</td>
            <td scope={"col"}>{props.term.category.name}</td>
            {/*treba da e manufacturer ama dava greska*/}
            <td scope={"col"}>{props.term.manufacturer.name}</td>
        </tr>
    );
}

export default product;