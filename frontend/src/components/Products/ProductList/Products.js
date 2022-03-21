import React from "react";
import Product from "../ProductTerm/Product";


const products = (props) => {
    return (
        <div className={"container col-md-3 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <td scope={"col"}>Name</td>
                            <td scope={"col"}>Price</td>
                            <td scope={"col"}>Quantity</td>
                            <td scope={"col"}>Category</td>
                            <td scope={"col"}>Manufacturer</td>
                        </tr>
                        </thead>
                        <tbody>
                        {props.products.map((term) => {
                            return (
                                <Product term={term}/>
                            );
                        })
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
export default products;